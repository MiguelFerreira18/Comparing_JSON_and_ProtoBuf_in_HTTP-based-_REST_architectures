#!/bin/bash

PROMETHEUS_URL="http://localhost:9090"
NAMESPACE="app-namespace-json"
OUTPUT_FILE="energy_consumption.csv"
STEP="1s"




usage(){
  echo "Usage: $0 -s <start_time> -e <end_time> [-u <prometheus_ur>] [-n <namespace>]"
  echo "  -s <start_time>   Start time in ISO format (e.g., '2023-05-09T16:55:00')"
  echo "  -e <end_time>     End time in ISO format (e.g., '2023-05-09T17:05:00')"
  echo "  -u <prometheus_url>  Prometheus URL (default: $PROMETHEUS_URL)"
  echo "  -n <namespace>    Namespace filter (default: all namespaces)"
  echo ""
  echo "Example:"
  echo "  $0 -s '2025-05-10T16:55:00' -e '2025-05-10T17:05:00' -u http://localhost:9090 -n app-namespace-json"
  exit 1
}

iso_to_unix(){
  if [[ "$1" =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2} ]]; then
    date -d "$1" +%s
  else
    echo "$1"
  fi
}

START_TIME=""
END_TIME=""

while getopts "s:e:u:n:" opt; do
  case ${opt} in
    s )
      START_TIME=$(iso_to_unix "$OPTARG")
      ;;
    e )
      END_TIME=$(iso_to_unix "$OPTARG")
      ;;
    u )
      PROMETHEUS_URL=$OPTARG
      ;;
    n )
      NAMESPACE=$OPTARG
      ;;
    \? )
      usage
      ;;
  esac
done

if [[ -z "$START_TIME" || -z "$END_TIME" ]]; then
  echo "Error: Missing required parameters."
  usage
fi


echo "Extracting Energy consumption from $NAMESPACE namespace"
echo "Using time range:"
echo "Start: $(date -d @$START_TIME) ($START_TIME)"
echo "End:   $(date -d @$END_TIME) ($END_TIME)"

##NOTE: THOSE ARE THE DEFAULT QUERIES FOR ENERGY CONSMPTION
declare -A QUERIES=(
  ["TOTAL"]="sum by (pod_name, container_namespace) (irate(kepler_container_joules_total{container_namespace=~\"$NAMESPACE\", pod_name=~\".*\"}[1m]))"
)

echo "Fetching data from Prometheus from $START_TIME to date -d $END_TIME..."

TMP_DIR=$(mktemp -d)

echo "Getting timestamps..."
curl -s -G "$PROMETHEUS_URL/api/v1/query_range" \
  --data-urlencode "query=${QUERIES[TOTAL]}" \
  --data-urlencode "start=$START_TIME" \
  --data-urlencode "end=$END_TIME" \
  --data-urlencode "step=$STEP" \
  | jq -r '.data.result[].values[] | .[0] | tostring' \
  > "$TMP_DIR/timestamps.txt"

echo "Getting data for each metric"
for METRIC in "${!QUERIES[@]}"; do
  echo "Fetching $METRIC data..."
  curl -s -G "$PROMETHEUS_URL/api/v1/query_range" \
    --data-urlencode "query=${QUERIES[$METRIC]}" \
    --data-urlencode "start=$START_TIME" \
    --data-urlencode "end=$END_TIME" \
    --data-urlencode "step=$STEP" \
    | jq -r '.data.result[].values[] | .[1]' \
    > "$TMP_DIR/$METRIC.txt"
done

echo "Creating CSV output..."
echo "Time,TOTAL" > "$OUTPUT_FILE"

paste -d, "$TMP_DIR/timestamps.txt" "$TMP_DIR/TOTAL.txt"  | while IFS=, read -r ts total; do
  # Convert Unix timestamp to human-readable format
  datetime=$(date -d "@$ts" "+%Y-%m-%d %H:%M:%S")
  # Write to CSV
  echo "$datetime,$total" >> "$OUTPUT_FILE"
done

rm -rf "$TMP_DIR"

echo "Data extraction complete. Results saved to $OUTPUT_FILE"
