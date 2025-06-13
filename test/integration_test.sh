#!/bin/bash

IS_TESTING_PROTOBUF=0
SCRIPT_DIR=$(dirname "$(readlink -f "$0")")
PARSE_PROTO_TO_JSON_SCRIPT="$(dirname $0)/proto_to_json.sh"
echo "Using script to parse protobuf to JSON: $PARSE_PROTO_TO_JSON_SCRIPT"
PORT=8080

PROTOS="protos.proto"

if [ -z "$1" ] || [ -z "$2" ]; then
    echo "Error: No serialisation specified"
    echo "Usage: $0 <serialisation> <bearer_token>"
    echo "       <serialisation> : Serialisation to test (either JSON or Protocol Buffers)"
    echo "       <bearer_token>   : Bearer token for authentication"
    exit 1
fi

if [[ "$1" == *"protobuf"* ]]; then
    IS_TESTING_PROTOBUF=1
fi

if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
    PORT=8081
fi

BEARER_TOKEN="$2"
BASE_URL="http://localhost:$PORT"
ENDPOINTS=(
    "products/entities?orderby=id&order=asc:GET"
    "users/{id}:GET"
    "users?orderby=id&order=asc:GET"
    "users:POST"
    "users/{id}:PUT"
    "users/{id}:DELETE"
)



parse_json_to_proto() {
    local json_file="$1"
    local json_name="$2"
    local encode_type="$3"
    local proto_file="$4"
    
    if [ ! -f "$json_file" ]; then
        echo "Error: JSON file '$json_file' does not exist."
        exit 1
    fi
    
    local temp_file=$(mktemp)
    
    jq -r '
      to_entries | map(
        "\(.key): \(
          .value |
          if type == "string" and (. == "ADMIN" or . == "EMPLOYEE") then .
          elif type == "string" then "\"\(.)\""
          else .
          end
        )"
      ) | join("\n")
    ' "$json_file" > "$temp_file"
    
    if [ $? -ne 0 ]; then
        echo "Error: Failed to process JSON file with jq"
        rm "$temp_file"
        exit 1
    fi
    
    protoc --encode="$encode_type" "$proto_file" < "$temp_file" > "output_$json_name.bin"
    
    if [ $? -ne 0 ]; then
        echo "Error: Failed to encode to protobuf"
        rm "$temp_file"
        exit 1
    fi
    
    rm "$temp_file"
    
    echo "Successfully converted $json_file to $proto_file"
}
print_proto_contents(){
    local proto_file="$1"
    local proto_type="$2"
    
    if [ ! -f "$proto_file" ]; then
        echo "Error: Protobuf file '$proto_file' does not exist."
        exit 1
    fi
    
    echo "Contents of $proto_file for type $proto_type:"
    protoc --decode="$proto_type" --proto_path=./test "protos.proto" < "$proto_file"
    
}
parse_proto_to_json() {
    local proto_type="$1"
    local protos_file="$2"
    local output_file="$3"
    
    local output_binary_file="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
    
    # print_proto_contents "$output_file" "$proto_type"
    
    json=$("$PARSE_PROTO_TO_JSON_SCRIPT" "$proto_type" "$protos_file" "$output_file")
    
    
    echo $json
}
assert_equal() {
    local expected="$1"
    local actual="$2"
    local test_name="$3"
    
    local normalized_expected=$(echo "$expected" | jq -c . 2>/dev/null || echo "$expected" )
    local normalized_actual=$(echo "$actual" | jq -c . 2>/dev/null || echo "$actual")
    
    if [ "$normalized_expected" != "$normalized_actual" ]; then
        echo "$test_name assertion failed. 🟥"
        dif_out=$(diff <(echo "$normalized_expected") <(echo "$normalized_actual"))
        echo -n "$dif_out" > "./test/diff/${test_name}_diff_output.txt"
        exit 1
    fi
    echo "$test_name assertion passed. 🟩"
}

# TESTS
test_get_all_product_entities(){
    local bearer_token="$1"
    local url="$2"
    local uri="$3"
    local output_bin_name="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
    
    local curl_request=$(curl --location "$url/$uri" \
        --header 'Accept: */*' \
        --header "Authorization: Bearer $bearer_token" \
    --output $output_bin_name)
    
    
    local curl_response=$(echo -s "$curl_request")
    
    
    local expected_response_file="./test/JSON/get_all_product_entities_expected.json"
    if [ ! -f "$expected_response_file" ]; then
        echo "Error: Expected response file '$expected_response_file' does not exist."
        exit 1
    fi
    
    local filtered_response
    local expected_response=$(cat "$expected_response_file")
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        local type="ProductEntity"
        json=$(parse_proto_to_json "$type" "$PROTOS" "$output_bin_name")
        echo "$json"
        filtered_response=$(echo "$json" | jq 'map(del(.createdAt, .updatedAt) | .products |= map(del(.createdAt, .updatedAt)))')
        assert_equal "$expected_response" "$filtered_response" "get_all_product_entities"
    else
        filtered_response=$(echo "$curl_response" | jq 'map(del(.createdAt, .updatedAt) | .products |= map(del(.createdAt, .updatedAt)))' 2>/dev/null) || filtered_response="$curl_response"
        assert_equal "$expected_response" "$filtered_response" "get_all_product_entities"
    fi
    
    rm -f "$output_bin_name"
    
}
# test_get_user_by_id(){

# }
# test_get_all_users(){

# }
# test_create_user(){

# }
# test_update_user(){

# }
# test_delete_user(){

# }

test_get_all_product_entities "$BEARER_TOKEN" "$BASE_URL" "products/entities?orderby=id&order=asc"


