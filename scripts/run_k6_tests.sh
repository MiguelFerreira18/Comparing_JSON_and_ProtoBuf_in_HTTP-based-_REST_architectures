#!/bin/bash

if [ -z "$1" ]; then
    echo "Error: Bearer token not provided"
    echo "Usage: $0 <auth_token>"
    exit 1
fi

AUTH_TOKEN=$1
ITERATIONS=(1000 10000) ##NOTE: If i have time add 5000
REPEATS=3
DELAY_BETWEEN_TESTS=5
EXTRACT_SCRIPT="./scripts/extract_from_dashboard.sh"

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
    local start_time=$(echo "$output" | grep "Test started at:" | awk -F': ' '{print $2}' | tr -d '\r')
    local end_time=$(echo "$output" | grep "Test ended at:" | awk -F': ' '{print $2}' | tr -d '\r')
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
                
                cmd="k6 run -e AUTH_TOKEN=$AUTH_TOKEN --iterations $iteration --out json=$json_report --out csv=$csv_report $script_path"
                
                echo "Running: $iteration iterations (repeat $repeat)"
                echo "Report directory: $report_dir"
                echo "Command: $cmd"
                
                output=$(eval "$cmd" 2>&1)
                test_result=$?
                
                if [ $test_result -eq 0 ]; then
                    echo "Test completed successfully"
                    
                    read start_time end_time <<< $(parse_timestamps "$output")
                    
                    if [ -n "$start_time" ] && [ -n "$end_time" ]; then
                        echo "Test ran from $start_time to $end_time"
                        
                        namespace=$(get_namespace "$test_name")
                        
                        extract_cmd="$EXTRACT_SCRIPT -s \"$start_time\" -e \"$end_time\" -n \"$namespace\""
                        echo "Executing: $extract_cmd"
                        
                        # Execute and wait for completion
                        eval "$extract_cmd"
                        extract_result=$?
                        
                        if [ $extract_result -ne 0 ]; then
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
