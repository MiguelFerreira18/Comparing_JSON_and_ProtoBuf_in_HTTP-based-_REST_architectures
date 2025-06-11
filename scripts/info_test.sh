#!/bin/bash

DIRECTORIES=(
    "./Control_Project_JSON/k6/json_get_all_products_entities/reports/json_get_all_products_entities"
    "./Control_Project_JSON/k6/json_get_user_id/reports/json_get_user_id"
    "./Control_Project_JSON/k6/json_get_all_users/reports/json_get_all_users"
    "./Control_Project_JSON/k6/json_create_user/reports/json_create_user"
    "./Control_Project_JSON/k6/json_update_user/reports/json_update_user"
    "./Control_Project_JSON/k6/json_delete_user/reports/json_delete_user"
    "./Experimental_Group_ProtoBuff/k6/protobuf_get_all_products_entities/reports/protobuf_get_all_products_entities"
    "./Experimental_Group_ProtoBuff/k6/protobuf_get_all_users/reports/protobuf_get_all_users"
    "./Experimental_Group_ProtoBuff/k6/protobuf_get_user_id/reports/protobuf_get_user_id"
    "./Experimental_Group_ProtoBuff/k6/protobuf_create_user/reports/protobuf_create_user"
    "./Experimental_Group_ProtoBuff/k6/protobuf_update_user/reports/protobuf_update_user"
    "./Experimental_Group_ProtoBuff/k6/protobuf_delete_user/reports/protobuf_delete_user"
    "./Gateway/k6_json/json_get_all_products_entities/reports/json_get_all_products_entities_gateway"
    "./Gateway/k6_json/json_get_all_users/reports/json_get_all_users_gateway"
    "./Gateway/k6_json/json_get_user_id/reports/json_get_user_id_gateway"
    "./Gateway/k6_json/json_create_user/reports/json_create_user_gateway"
    "./Gateway/k6_json/json_update_user/reports/json_update_user_gateway"
    "./Gateway/k6_json/json_delete_user/reports/json_delete_user_gateway"
    "./Gateway/k6_protobuf/protobuf_get_all_products_entities/reports/protobuf_get_all_products_entities_gateway"
    "./Gateway/k6_protobuf/protobuf_get_all_users/reports/protobuf_get_all_users_gateway"
    "./Gateway/k6_protobuf/protobuf_get_user_id/reports/protobuf_get_user_id_gateway"
    "./Gateway/k6_protobuf/protobuf_create_user/reports/protobuf_create_user_gateway"
    "./Gateway/k6_protobuf/protobuf_update_user/reports/protobuf_update_user_gateway"
    "./Gateway/k6_protobuf/protobuf_delete_user/reports/protobuf_delete_user_gateway"
)
calculate_median() {
    local sorted_data=($(printf '%s\n' "$@" | sort -n))
    local count=${#sorted_data[@]}
    local median=0
    
    if (( count % 2 == 1 )); then
        median=${sorted_data[((count/2))]}
    else
        median=$(echo "scale=4; (${sorted_data[((count/2-1))]} + ${sorted_data[((count/2))]}) / 2" | bc -l)
    fi
    
    echo "$median"
}

# Function to extract base test name from path (removes json/protobuf prefixes)
get_base_test_name() {
    local path="$1"
    local test_name
    
    # Extract the test name part (like "get_all_users")
    if [[ "$path" =~ \/(json_|protobuf_)?([^/]+)(_gateway)?$ ]]; then
        test_name="${BASH_REMATCH[2]}"
    fi
    
    # Convert to uppercase and replace underscores with spaces
    test_name=$(echo "$test_name" | tr '[:lower:]' '[:upper:]' | tr '_' ' ')
    echo "$test_name"
}

# Function to determine if path is JSON or Protobuf
get_test_type() {
    local path="$1"
    
    if [[ "$path" == *"json"* ]]; then
        echo "JSON"
    elif [[ "$path" == *"protobuf"* ]]; then
        echo "PROTOBUF"
    fi
}

# Function to determine if path is gateway
is_gateway() {
    local path="$1"
    [[ "$path" == *"gateway"* ]] || [[ "$path" == *"Gateway"* ]]
}

# Arrays to store all results
declare -A all_results

process_directory() {
    local base_dir="$1"
    local dir_name=$(basename "$base_dir")
    
    # Get base test name (without json/protobuf prefix)
    local base_test_name=$(get_base_test_name "$base_dir")
    local test_type=$(get_test_type "$base_dir")
    local gateway_suffix=""
    
    if is_gateway "$base_dir"; then
        gateway_suffix=" + Gateway"
    fi
    
    echo ""
    echo "================================================================="
    echo "PROCESSING: $base_test_name - $test_type$gateway_suffix"
    echo "================================================================="
    
    # (1000, 10000)
    for iter_dir in "${base_dir}/1000" "${base_dir}/10000"; do
        if [ ! -d "$iter_dir" ]; then
            echo "  - Iteration directory $iter_dir not found, skipping..."
            continue
        fi
        
        iteration=$(basename "$iter_dir")
        echo "  -----------------------------------------"
        echo "  ITERATION: $iteration"
        echo "  -----------------------------------------"
        
        energy_diffs=()
        trial_names=()
        
        for trial_dir in "${iter_dir}"/report_*; do
            if [ ! -d "$trial_dir" ]; then
                continue
            fi
            
            trial_name=$(basename "$trial_dir")
            trial_names+=("$trial_name")
            
            before_file="${trial_dir}/before.txt"
            after_file="${trial_dir}/after.txt"
            
            if [ ! -f "$before_file" ] || [ ! -f "$after_file" ]; then
                echo "    - Missing before.txt or after.txt in $trial_name, skipping..."
                energy_diffs+=("NA")
                continue
            fi
            
            # Get main service energy
            before_energy=$(grep 'kepler_container_joules_total.*mode="dynamic"' "$before_file" | awk '{print $2}')
            after_energy=$(grep 'kepler_container_joules_total.*mode="dynamic"' "$after_file" | awk '{print $2}')
            
            if [ -z "$before_energy" ] || [ -z "$after_energy" ]; then
                echo "    - Could not extract main service energy values from $trial_name, skipping..."
                energy_diffs+=("NA")
                continue
            fi
            
            energy_diff=$(echo "$after_energy - $before_energy" | bc -l)
            
            # If gateway, add gateway energy
            if is_gateway "$base_dir"; then
                before_gateway_file="${trial_dir}/before_gateway.txt"
                after_gateway_file="${trial_dir}/after_gateway.txt"
                
                if [ ! -f "$before_gateway_file" ] || [ ! -f "$after_gateway_file" ]; then
                    echo "    - Missing gateway energy files in $trial_name, skipping..."
                    energy_diffs+=("NA")
                    continue
                fi
                
                before_gateway_energy=$(grep 'kepler_container_joules_total.*mode="dynamic"' "$before_gateway_file" | awk '{print $2}')
                after_gateway_energy=$(grep 'kepler_container_joules_total.*mode="dynamic"' "$after_gateway_file" | awk '{print $2}')
                
                if [ -z "$before_gateway_energy" ] || [ -z "$after_gateway_energy" ]; then
                    echo "    - Could not extract gateway energy values from $trial_name, skipping..."
                    energy_diffs+=("NA")
                    continue
                fi
                
                gateway_diff=$(echo "$after_gateway_energy - $before_gateway_energy" | bc -l)
                total_diff=$(echo "$energy_diff + $gateway_diff" | bc -l)
                
                echo "    - $trial_name: Energy consumed = $total_diff joules (service: $energy_diff + gateway: $gateway_diff)"
                energy_diffs+=("$total_diff")
            else
                echo "    - $trial_name: Energy consumed = $energy_diff joules"
                energy_diffs+=("$energy_diff")
            fi
        done
        
        valid_data=()
        for diff in "${energy_diffs[@]}"; do
            if [ "$diff" != "NA" ]; then
                valid_data+=("$diff")
            fi
        done
        
        if [ ${#valid_data[@]} -eq 0 ]; then
            echo "    - No valid data found for iteration $iteration"
            continue
        fi
        
        sum=0
        count=${#valid_data[@]}
        for diff in "${valid_data[@]}"; do
            sum=$(echo "$sum + $diff" | bc -l)
        done
        mean=$(echo "$sum / $count" | bc -l)
        
        variance_sum=0
        for diff in "${valid_data[@]}"; do
            deviation=$(echo "$diff - $mean" | bc -l)
            squared_deviation=$(echo "$deviation * $deviation" | bc -l)
            variance_sum=$(echo "$variance_sum + $squared_deviation" | bc -l)
        done
        variance=$(echo "$variance_sum / $count" | bc -l)
        stddev=$(echo "sqrt($variance)" | bc -l)
        
        min=${valid_data[0]}
        max=${valid_data[0]}
        for diff in "${valid_data[@]}"; do
            if (( $(echo "$diff < $min" | bc -l) )); then
                min=$diff
            fi
            if (( $(echo "$diff > $max" | bc -l) )); then
                max=$diff
            fi
        done
        
        median=$(calculate_median "${valid_data[@]}")
        
        # Store results for later table generation
        key="$base_test_name|$test_type$gateway_suffix|$iteration"
        all_results["$key|min"]=$min
        all_results["$key|max"]=$max
        all_results["$key|mean"]=$mean
        all_results["$key|median"]=$median
        all_results["$key|stddev"]=$stddev
        
        echo ""
        echo "    SUMMARY FOR $iteration ITERATIONS:"
        echo "    ---------------------------------"
        echo "    Number of valid trials: $count"
        echo "    Minimum energy: $min joules"
        echo "    Maximum energy: $max joules"
        echo "    Mean energy consumption: $mean joules"
        echo "    Median energy consumption: $median joules"
        echo "    Standard deviation: $stddev joules"
        echo ""
    done
}

# Function to print comparison tables
print_comparison_tables() {
    # Get unique base test names
    declare -A base_test_names
    for key in "${!all_results[@]}"; do
        IFS='|' read -ra parts <<< "$key"
        base_test_names["${parts[0]}"]=1
    done
    
    # For each base test name, print a comparison table
    for base_test_name in "${!base_test_names[@]}"; do
        echo ""
        echo "================================================================"
        echo "COMPARISON TABLE FOR: $base_test_name"
        echo "================================================================"
        echo ""
        printf "| %-20s | %-6s | %-8s | %-8s | %-8s | %-8s | %-8s |\n" \
               "Test" "Reqs" "Min" "Max" "Mean" "Median" "Std"
        echo "|----------------------|--------|----------|----------|----------|----------|----------|"
        
        # Print all variants for this endpoint
        print_test_results "$base_test_name" "JSON" "1000"
        print_test_results "$base_test_name" "PROTOBUF" "1000"
        print_test_results "$base_test_name" "JSON + Gateway" "1000"
        print_test_results "$base_test_name" "PROTOBUF + Gateway" "1000"
        
        print_test_results "$base_test_name" "JSON" "10000"
        print_test_results "$base_test_name" "PROTOBUF" "10000"
        print_test_results "$base_test_name" "JSON + Gateway" "10000"
        print_test_results "$base_test_name" "PROTOBUF + Gateway" "10000"
        
        echo ""
    done
}

# Function to print a row of test results
print_test_results() {
    local base_test_name="$1"
    local test_type="$2"
    local iteration="$3"
    
    local key="$base_test_name|$test_type|$iteration"
    
    if [[ -z "${all_results["$key|min"]}" ]]; then
        return  # No data for this combination
    fi
    
    printf "| %-20s | %-6s | %-8.2f | %-8.2f | %-8.2f | %-8.2f | %-8.2f |\n" \
           "$test_type" "$iteration" \
           "${all_results["$key|min"]}" \
           "${all_results["$key|max"]}" \
           "${all_results["$key|mean"]}" \
           "${all_results["$key|median"]}" \
           "${all_results["$key|stddev"]}"
}

# Main processing
for dir in "${DIRECTORIES[@]}"; do
    if [ ! -d "$dir" ]; then
        echo "Directory $dir not found, skipping..."
        continue
    fi
    process_directory "$dir"
done

# Print all comparison tables
print_comparison_tables

echo ""
echo "================================================================="
echo "ALL PROCESSING COMPLETE"
echo "================================================================="
