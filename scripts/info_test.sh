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

process_directory() {
    local base_dir="$1"
    local dir_name=$(basename "$base_dir")
    local is_gateway=false

    if [[ "$base_dir" == *"gateway"* ]] || [[ "$base_dir" == *"Gateway"* ]]; then 
        is_gateway=true
    fi

    echo ""
    echo "================================================================="
    echo "PROCESSING DIRECTORY: $dir_name"
    if $is_gateway; then
        echo " (GATEWAY - WILL INCLUDE GATEWAY ENERGY IN CALCULATIONS)"
    fi
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
            
            # trial name/number
            trial_name=$(basename "$trial_dir")
            trial_names+=("$trial_name")
            
            before_file="${trial_dir}/before.txt"
            after_file="${trial_dir}/after.txt"
            
            if [ ! -f "$before_file" ] || [ ! -f "$after_file" ]; then
                echo "    - Missing before.txt or after.txt in $trial_name, skipping..."
                energy_diffs+=("NA")
                continue
            fi
            
            before_energy=$(grep 'kepler_container_joules_total.*mode="dynamic"' "$before_file" | awk '{print $2}')
            after_energy=$(grep 'kepler_container_joules_total.*mode="dynamic"' "$after_file" | awk '{print $2}')
            
            if [ -z "$before_energy" ] || [ -z "$after_energy" ]; then
                echo "    - Could not extract energy values from $trial_name, skipping..."
                energy_diffs+=("NA")
                continue
            fi
            
            energy_diff=$(echo "$after_energy - $before_energy" | bc -l)
            
            if $is_gateway; then
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
        
        echo ""
        echo "    SUMMARY FOR $iteration ITERATIONS:"
        echo "    ---------------------------------"
        echo "    Number of valid trials: $count"
        echo "    Minimum energy: $min joules"
        echo "    Maximum energy: $max joules"
        echo "    Mean energy consumption: $mean joules"
        echo "    Standard deviation: $stddev joules"
        echo ""
    done
}

for dir in "${DIRECTORIES[@]}"; do
    if [ ! -d "$dir" ]; then
        echo "Directory $dir not found, skipping..."
        continue
    fi
    process_directory "$dir"
done

echo ""
echo "================================================================="
echo "ALL PROCESSING COMPLETE"
echo "================================================================="
