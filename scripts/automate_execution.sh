#!/bin/bash

# Check commands existance
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

wait_for_pod() {
    local namespace=$1
    local label_selector=$2
    local pod_name
    echo "Waiting for pod with label $label_selector in namespace $namespace to be ready..."

    while true; do
        pod_name=$(kubectl get pod -n "$namespace" -l "$label_selector" -o jsonpath="{.items[0].metadata.name}" 2>/dev/null)
        if [ -n "$pod_name" ]; then
            break
        fi
        sleep 2
    done

    kubectl wait --for=condition=Ready pod/"$pod_name" --timeout=-1s -n "$namespace"
    echo "Pod $pod_name is ready."
}

# WARNING: If on windows
if [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" ]]; then
    echo -e "\n Windows detected, starting Docker Desktop..."
    if command_exists docker; then
        if ! docker info >/dev/null 2>&1; then
            echo "Starting Docker Desktop..."
            "/c/Program Files/Docker/Docker/Docker Desktop.exe" &
            # Wait for Docker to start
            while ! docker info >/dev/null 2>&1; do
                echo "Waiting for Docker to start..."
                sleep 5
            done
        else
            echo "Docker is already running."
        fi
    else
        echo "Docker not found. Please install Docker Desktop first."
        exit 1
    fi
fi

echo -e "\n Starting Minikube..."
if command_exists minikube; then
    if ! minikube status | grep -q "Running"; then
        minikube start
    else
        echo "Minikube is already running."
    fi
else
    echo "Minikube not found. Please install Minikube first."
    exit 1
fi

echo -e "\n Installing Prometheus stack"
if  ! helm list -n monitoring | grep -q "prometheus"; then
    helm install prometheus prometheus-community/kube-prometheus-stack \
        --namespace monitoring \
        --create-namespace
        else
            echo "Prometheus stack is already installed"
fi

echo -e "\n Installing Kepler..."
if ! helm list -n kepler | grep -q "kepler"; then
    helm install kepler kepler/kepler \
        --namespace kepler \
        --create-namespace \
        --set serviceMonitor.enabled=true \
        --set serviceMonitor.labels.release=prometheus
else
    echo "Kepler is already installed."
fi

wait_for_pod "kepler" "app.kubernetes.io/name=kepler"
wait_for_pod "monitoring" "app.kubernetes.io/name=grafana,app.kubernetes.io/instance=prometheus"

echo -e "\n Import dashboards into to grafana"

dashboards=("Kepler-Exporter.json" "Springboot-Dashboard.json")

if command_exists kubectl; then
    GRAFANA_POD=$(kubectl get pods -n monitoring -l "app.kubernetes.io/name=grafana,app.kubernetes.io/instance=prometheus" -o jsonpath="{.items[0].metadata.name}")
    if [ -n "$GRAFANA_POD" ]; then
        for dashboard in "${dashboards[@]}"; do
            if ! kubectl exec -n monitoring "$GRAFANA_POD" -- ls /var/lib/grafana/dashboard/"$dashboard" >/dev/null 2>&1; then
                if  [ -f "$dashboard" ]; then
                echo "Copying dashboard $dashboard to Grafana pod..."
                kubectl cp "$dashboard" -n monitoring "$GRAFANA_POD:/tmp/dashboards/$dashboard"
                else
                echo "Warning: $dashboard not found in current directory. Dashboard not imported."
                fi
            else
                echo "$dashboard dashboard already exists in Grafana"
            fi
        done
    else
       echo "Grafana pod not found. Dashboard not imported." 
    fi
fi

echo -e "\n Setting up Grafana port-forward in background..."
GRAFANA_POD=$(kubectl get pods -n monitoring -l "app.kubernetes.io/name=grafana,app.kubernetes.io/instance=prometheus" -o jsonpath="{.items[0].metadata.name}")
if [ -n "$GRAFANA_POD" ]; then
    kubectl port-forward -n monitoring "pod/$GRAFANA_POD" 3000:3000 >/dev/null 2>&1 &
    echo "Grafana is available at http://localhost:3000"
    echo "Default credentials: admin/prom-operator"
else
    echo "Grafana pod not found. Port-forward not set up."
fi


echo -e "\n Applying Kubernetes manifests from directories..."
declare -a k8s_dirs=("Control_Project_JSON/k8s" "Experimental_Group_ProtoBuff/k8s" "Gateway/k8s")
for dir in "${k8s_dirs[@]}"; do
    if [ -d "$dir" ]; then
        echo "Applying manifests in $dir"
        kubectl apply -f "$dir"
    else
        echo "Directory $dir not found. Skipping..."
    fi
done

echo -e "\n Setting up port-forwards for services..."
services=(
    "monitoring:prometheus-operated:9090:9090"
    "gateway-namespace:gateway:8082:8082"
    "app-namespace-protobuf:experimental-project:8081:8081"
    "app-namespace-json:control-project:8080:8080"
    "kepler:kepler:9102:9102" # KEPLER
    )
for svc in "${services[@]}"; do
   IFS=":" read -r namespace name local_port target_port <<< "$svc"
    if kubectl get svc "$name" -n "$namespace" >/dev/null 2>&1 ; then
        kubectl port-forward "svc/$name" "$local_port:$target_port" -n "$namespace" >/dev/null 2>&1 & 
        echo "$name service is available at http://localhost:$local_port"
    else
        echo "Service $name in namespace $namespace not found. Port-forward not set up."
    fi
done

echo -e "\n Setup complete!"
echo "Summary of port-forwards:"
echo "- Grafana: http://localhost:3000"
for svc in "${services[@]}"; do
    IFS=":" read -r namespace name local_port target_port <<< "$svc"
    echo "- $name: http://localhost:$local_port"
done

