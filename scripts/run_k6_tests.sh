#!/bin/bash

if [ -z "$1" ]; then
    echo "Error: Bearer token not provided"
    echo "Usage: $0 <auth_token>"
    exit 1
fi

AUTH_TOKEN=$1
ITERATIONS=(1000 10000) ##NOTE: If i have time add 5000
VU=1
DURATION=40
SUMMARY_TIME_UNIT="ms"
REPEATS=3
DELAY_BETWEEN_TESTS=5
EXTRACT_SCRIPT="./scripts/extract_from_dashboard.sh"
EXTRACTED_CSV="energy_consumption.csv"
SUMMARY_HTML="summary.html"
SUMMARY_JSON="summary.json"

declare -A K6_SCRIPTS=(
    ["json_create_user"]="./Control_Project_JSON/k6/json_create_user/json_create_user_k6.js"
    ["json_get_all_products_entities"]="./Control_Project_JSON/k6/json_get_all_products_entities/json_get_all_products_entities_k6.js"
    ["json_get_all_users"]="./Control_Project_JSON/k6/json_get_all_users/json_get_all_users_k6.js"
    ["json_get_user_id"]="./Control_Project_JSON/k6/json_get_user_id/json_get_user_id_k6.js"
    # ["protobuf_create_user"]="./Experimental_Group_ProtoBuff/k6/protobuf_create_user/protobuf_create_user_bundle/dist/k6.bundle.js" ##WARNING: ADD AFTER EVERYTHING IS WORKING
    ["protobuf_get_all_products_entities"]="./Experimental_Group_ProtoBuff/k6/protobuf_get_all_products_entities/protobuf_get_all_products_entities_k6.js"
    ["protobuf_get_all_users"]="./Experimental_Group_ProtoBuff/k6/protobuf_get_all_users/protobuf_get_all_users_k6.js"
    ["protobuf_get_user_id"]="./Experimental_Group_ProtoBuff/k6/protobuf_get_user_id/protobuf_get_user_id_k6.js"
)
parse_timestamps() {
    local output="$1"
    local start_time=$(echo "$output" | grep "Test started at:" | sed -E 's/.*Test started at: ([^"]+).*/\1/')
    local end_time=$(echo "$output" | grep "Test ended at:" | sed -E 's/.*Test ended at: ([^"]+).*/\1/')
    echo "$start_time $end_time"
}
get_namespace() {
    local test_name="$1"
    if [[ "$test_name" == *"json"* ]]; then
        echo "app-namespace-json"
    else
        echo "app-namespace-protobuf"
    fi
}
run_k6_tests() {
    for test_name in "${!K6_SCRIPTS[@]}"; do
        script_path="${K6_SCRIPTS[$test_name]}"
        
        if [ ! -f "$script_path" ]; then
            echo "Warning: Test script not found - $script_path"
            continue
        fi
        
        echo -e "\nRunning tests for: $test_name"
        
        base_report_dir="$(dirname "$script_path")/reports/$test_name"
        
        for iteration in "${ITERATIONS[@]}"; do
            for ((repeat=1; repeat<=$REPEATS; repeat++)); do
                report_dir="$base_report_dir/$iteration/report_$repeat"
                mkdir -p "$report_dir"
                
                json_report="$report_dir/data.json"
                csv_report="$report_dir/data.csv"
                
                cmd="k6 run -e AUTH_TOKEN=$AUTH_TOKEN -e ITERATIONS=$iteration -i $ITERATIONS -d ${DURATION}m -u $VU --summary-time-unit $SUMMARY_TIME_UNIT --out json=$json_report --out csv=$csv_report $script_path"
                
                echo "Running: $iteration iterations (repeat $repeat)"
                echo "Report directory: $report_dir"
                echo "Command: $cmd"
                
                output=$(eval "$cmd" 2>&1)
                test_result=$?
                
                if [ $test_result -eq 0 ]; then
                    echo -e "\nTest completed successfully"

                    if [ -f "$SUMMARY_HTML" ] && [ -f "$SUMMARY_JSON" ]; then
                        mv "$SUMMARY_HTML" "$report_dir/summary.html"
                        mv "$SUMMARY_JSON" "$report_dir/summary.json"
                        echo "Moved html and json summary to $report_dir"
                    else
                        echo "Warning: not html and json summary found"

                    fi
                    
                    read start_time end_time <<< $(parse_timestamps "$output")
                    
                    if [ -n "$start_time" ] && [ -n "$end_time" ]; then
                        echo "Test ran from $start_time to $end_time"
                        
                        namespace=$(get_namespace "$test_name")
                        
                        extract_cmd="$EXTRACT_SCRIPT -s \"$start_time\" -e \"$end_time\" -n \"$namespace\""
                        echo -e "\n Executing: $extract_cmd"
                        
                        # Execute and wait for completion
                        eval "$extract_cmd"
                        extract_result=$?
                        
                        if [ $extract_result -eq 0 ]; then
                            if [ -f "$EXTRACTED_CSV" ]; then
                                mv "$EXTRACTED_CSV" "$report_dir/power_consumption_${test_name}.csv"
                                echo "Moved metrics results to $report_dir/power_consumption_${test_name}.csv"
                            else
                                echo "Warning: Extracted CSV not found at $EXTRACTED_CSV"
                            fi
                        else
                            echo "Warning: Extraction script failed with code $extract_result"
                        fi
                    else
                        echo "Warning: Could not parse timestamps from test output"
                    fi
                else
                    echo "Error running test (code $test_result)"
                fi
                
                echo "Waiting $DELAY_BETWEEN_TESTS seconds before next test..."
                sleep $DELAY_BETWEEN_TESTS
            done
        done
    done
}

# Main execution
echo "Starting k6 test automation..."
echo "Will run ${#K6_SCRIPTS[@]} test scripts:"
for test_name in "${!K6_SCRIPTS[@]}"; do
    echo "- $test_name: ${K6_SCRIPTS[$test_name]}"
done

run_k6_tests
echo -e "\nAll tests completed!"
