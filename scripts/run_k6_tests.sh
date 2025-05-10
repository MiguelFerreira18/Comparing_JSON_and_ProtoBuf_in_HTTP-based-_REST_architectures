#!/bin/bash

# Check if bearer token is provided
if [ -z "$1" ]; then
    echo "Error: Bearer token not provided"
    echo "Usage: $0 <auth_token>"
    exit 1
fi

AUTH_TOKEN=$1
ITERATIONS=(1000 10000)  # Add 5000 later if needed
REPEATS=3
K6_BASE_CMD="k6 run -e AUTH_TOKEN=$AUTH_TOKEN"

# Function to find all k6 test scripts
find_k6_scripts() {
    local base_dir=${1:-.}
    find "$base_dir" -path "*/k6/*" -name "*.js" | while read -r script; do
        # Get the test name (format_endpoint) from directory name
        dir=$(dirname "$script")
        test_name=$(basename "$dir")
        echo "$test_name|$script"
    done
}

# Function to run k6 tests
run_k6_tests() {
    local scripts=$1
    
    echo "$scripts" | while IFS="|" read -r test_name script_path; do
        echo -e "\nRunning tests for: $test_name"
        
        # Create reports directory if it doesn't exist
        report_dir=$(dirname "$script_path")/reports
        mkdir -p "$report_dir"
        
        for iteration in "${ITERATIONS[@]}"; do
            for ((repeat=1; repeat<=$REPEATS; repeat++)); do
                # Generate report filename
                report_file="$report_dir/${test_name}_report_k6_${repeat}_${iteration}.json"
                
                # Build the k6 command
                cmd="$K6_BASE_CMD --iterations $iteration --out json=$report_file $script_path"
                
                echo "Running: $iteration iterations (repeat $repeat)"
                echo "Command: $cmd"
                
                # Execute the command
                if eval "$cmd"; then
                    echo "Test completed successfully"
                else
                    echo "Error running test"
                fi
            done
        done
    done
}

# Main execution
echo "Finding k6 test scripts..."
k6_scripts=$(find_k6_scripts)

if [ -z "$k6_scripts" ]; then
    echo "No k6 test scripts found!"
    exit 1
fi

echo -e "\nFound $(echo "$k6_scripts" | wc -l) test scripts:"
echo "$k6_scripts" | while IFS="|" read -r name path; do
    echo "- $name: $path"
done

run_k6_tests "$k6_scripts"
echo -e "\nAll tests completed!"
