#!/bin/bash

# TODO: PROTOBUF TESTS REQUIRE A PARSER, WHICH DOES NOT EXIST YET

IS_TESTING_PROTOBUF=0
SCRIPT_DIR=$(dirname "$(readlink -f "$0")")
PARSE_PROTO_TO_JSON_SCRIPT="$(dirname $0)/proto_to_json.sh"
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
    proto_dir="$(realpath "${SCRIPT_DIR}")"
    proto_file="${proto_dir}/${PROTOS}"
    
    if [ ! -f "$json_file" ]; then
        echo "Error: JSON file '$json_file' does not exist."
        exit 1
    fi
    local proto_file=$(find "$proto_dir" -name '*.proto' -print -quit)
    if [ -z "$proto_file" ]; then
        echo "Error: No .proto files found in $proto_dir"
        return 1
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
    protoc --encode="$encode_type" --proto_path="$proto_dir" "$proto_file" < "$temp_file" > "./test/output_$json_name.bin"
    
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
    protoc --decode="$proto_type" --proto_path=./ "protos.proto" < "$proto_file"
    
}
parse_proto_to_json() {
    local proto_type="$1"
    local protos_file="$2"
    local output_file="$3"
    
    json=$("$PARSE_PROTO_TO_JSON_SCRIPT" "$proto_type" "$protos_file" "$output_file")
    
    echo $json
}
assert_equal_file() {
    local expected="$1"
    local actual="$2"
    local test_name="$3"
    
    local normalized_expected=$(echo "$expected" | jq -c . 2>/dev/null || echo "$expected" )
    local normalized_actual=$(echo "$actual" | jq -c . 2>/dev/null || echo "$actual")
    
    if [ "$normalized_expected" != "$normalized_actual" ]; then
        echo "$test_name assertion failed. 🟥"
        dif_out=$(diff <(echo "$normalized_expected") <(echo "$normalized_actual"))
        echo -n "$dif_out" > "./test/diff/${test_name}_diff_output.txt"
    else
        echo "$test_name assertion passed. 🟩"
    fi
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
    )
    
    local curl_response=$(echo "$curl_request")
    
    local expected_response_file="./test/JSON/get_all_product_entities_expected.json"
    if [ ! -f "$expected_response_file" ]; then
        echo "Error: Expected response file '$expected_response_file' does not exist."
        echo "get_all_product_entities assertion failed. 🟥"
        return 1
    fi
    
    local filtered_response
    local expected_response=$(cat "$expected_response_file")
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        local type="ProductEntity"
        json=$(parse_proto_to_json "$type" "$PROTOS" "$output_bin_name")
        filtered_response=$(echo "$json" | jq 'map(del(.createdAt, .updatedAt) | .products |= map(del(.createdAt, .updatedAt)))')
        assert_equal_file "$expected_response" "$filtered_response" "get_all_product_entities"
    else
        filtered_response=$(echo "$curl_response" | jq 'map(del(.createdAt, .updatedAt) | .products |= map(del(.createdAt, .updatedAt)))') || filtered_response="$curl_response"
        assert_equal_file "$expected_response" "$filtered_response" "get_all_product_entities"
    fi
    
}
test_get_user_by_id(){
    local bearer_token="$1"
    local url="$2"
    local uri="$3"
    local curl_extras=()
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        output_bin_name="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
        curl_extras+=(--output "$output_bin_name")
    fi
    
    local curl_request=$(curl --location "$url/$uri" \
        --header 'Accept: */*' \
        --header "Authorization: Bearer $bearer_token" \
        "${curl_extras[@]}" \
    )
    
    
    
    local curl_response=$(echo "$curl_request")
    
    local expected_response_file="./test/JSON/get_user_id_expected.json"
    if [ ! -f "$expected_response_file" ]; then
        echo "Error: Expected response file '$expected_response_file' does not exist."
        echo "get_user_id assertion failed. 🟥"
        return 1
    fi
    
    local filtered_response
    local expected_response=$(cat "$expected_response_file")
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        local type="UserResponseDTO"
        json=$(parse_proto_to_json "$type" "${SCRIPT_DIR}/${PROTOS}" "$output_bin_name")
        echo "$json"
        filtered_response=$(echo "$json" | jq 'del(.createdAt, .updatedAt)')
        assert_equal_file "$expected_response" "$filtered_response" "get_user_id"
        
    else
        filtered_response=$(echo "$curl_response" | jq 'del(.createdAt, .updatedAt)') || filtered_response="$curl_response"
        assert_equal_file "$expected_response" "$filtered_response" "get_user_id"
        
    fi
    
}
test_get_all_users(){
    local bearer_token="$1"
    local url="$2"
    local uri="$3"
    local output_bin_name="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
    local curl_extras=()
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        curl_extras+=(--output "$output_bin_name")
    else
        local user_data=$(cat "$user_data_file")
    fi
    
    local curl_request=$(curl --location "$url/$uri" \
        --header 'Accept: */*' \
        --header "Authorization: Bearer $bearer_token" \
        "${curl_extras[@]}" \
    )
    
    local curl_response=$(echo "$curl_request")
    
    local expected_response_file="./test/JSON/get_all_users_expected.json"
    if [ ! -f "$expected_response_file" ]; then
        echo "Error: Expected response file '$expected_response_file' does not exist."
        echo "get_all_users assertion failed. 🟥"
        return 1
    fi
    
    local filtered_response
    local expected_response=$(cat "$expected_response_file")
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        local type="UserResponseCatalog"
        json=$(parse_proto_to_json "$type" "$PROTOS" "$output_bin_name")
        filtered_response=$(echo "$json" | jq 'del(.createdAt, .updatedAt)')
        # TODO: MAKE A SPECIAL SCRIPT FOR ARRAY DATA AND NOT OBJECT DATA
        assert_equal_file "$expected_response" "$filtered_response" "get_all_users"
        rm -f "$output_bin_name"
    else
        filtered_response=$(echo "$curl_response" | jq 'map(del(.createdAt, .updatedAt))') || filtered_response="$curl_response"
        assert_equal_file "$expected_response" "$filtered_response" "get_all_users"
    fi
}
test_create_user(){
    local bearer_token="$1"
    local url="$2"
    local uri="$3"
    local user_data_file="./test/JSON/post_user.json"
    local output_bin_name="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
    local content_type="application/json"
    local curl_extras=()
    proto_dir="$(realpath "${SCRIPT_DIR}/${PROTOS}")"
    
    printf "\n\n"
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        output_bin_name="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
        content_type="application/x-protobuf"
        
        local_bin_name="post_user"
        user_proto_data="$(parse_json_to_proto "$user_data_file" "$local_bin_name" "UserDTO")"
        user_bin_file="${SCRIPT_DIR}/output_post_user.bin"
        cat "$user_proto_data"
        curl_extras+=(--data-binary "@$user_bin_file")
        curl_extras+=(--output "$output_bin_name")
    else
        local user_data=$(cat "$user_data_file")
        curl_extras+=(--data "$user_data")
    fi
    
    printf "\n\n"
    
    
    local curl_request=$(curl -X POST --location "$url/$uri" \
        --header 'Accept: */*' \
        --header "Authorization: Bearer $bearer_token" \
        --header "Content-Type: $content_type" \
        "${curl_extras[@]}" \
    )
    
    local curl_response=$(echo "$curl_request")
    
    printf "\n\n"
    
    local expected_response_file="./test/JSON/post_user_expected.json"
    if [ ! -f "$expected_response_file" ]; then
        echo "Error: Expected response file '$expected_response_file' does not exist."
        echo "create_user assertion failed. 🟥"
        return 1
    fi
    
    local filtered_response
    local expected_response=$(cat "$expected_response_file")
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        local type="UserResponseDTO"
        json=$(parse_proto_to_json "$type" "$PROTOS" "$output_bin_name")
        filtered_response=$(echo "$json" | jq 'del(.createdAt, .updatedAt)')
        assert_equal_file "$expected_response" "$filtered_response" "create_user"
        rm -f "$output_bin_name"
        rm -f "$user_bin_file"
    else
        filtered_response=$(echo "$curl_response" | jq 'del(.createdAt, .updatedAt)') || filtered_response="$curl_response"
        assert_equal_file "$expected_response" "$filtered_response" "create_user"
    fi
}
test_update_user(){
    local bearer_token="$1"
    local url="$2"
    local uri="$3"
    local user_data_file="./test/JSON/put_user.json"
    local output_bin_name="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
    local content_type="application/json"
    local curl_extras=()
    proto_dir="$(realpath "${SCRIPT_DIR}/${PROTOS}")"
    
    if [ ! -f "$user_data_file" ]; then
        echo "Error: User data file '$user_data_file' does not exist."
        echo "update_user assertion failed. 🟥"
        return 1
    fi
    
    printf "\n\n"
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        content_type="application/x-protobuf"
        
        local_bin_name="put_user"
        user_proto_data="$(parse_json_to_proto "$user_data_file" "$local_bin_name" "EditUserDTO")"
        user_bin_file="${SCRIPT_DIR}/output_put_user.bin"
        curl_extras+=(--data-binary "@$user_bin_file")
        curl_extras+=(--output "$output_bin_name")
    else
        local user_data=$(cat "$user_data_file")
        curl_extras+=(--data "$user_data")
    fi
    
    printf "\n\n"
    
    local user_data=$(cat "$user_data_file")
    
    local curl_request=$(curl --location --globoff --request PUT "$url/$uri" \
        --header 'Accept: */*' \
        --header "Authorization: Bearer $bearer_token" \
        --header "Content-Type: $content_type" \
        "${curl_extras[@]}" \
    )
    
    local curl_response=$(echo "$curl_request")
    
    local expected_response_file="./test/JSON/put_user_expected.json"
    if [ ! -f "$expected_response_file" ]; then
        echo "Error: Expected response file '$expected_response_file' does not exist."
        echo "update_user assertion failed. 🟥"
        return 1
    fi
    
    local filtered_response
    local expected_response=$(cat "$expected_response_file")
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        local type="UserResponseDTO"
        json=$(parse_proto_to_json "$type" "$PROTOS" "$output_bin_name")
        filtered_response=$(echo "$json" | jq 'del(.createdAt, .updatedAt)')
        assert_equal_file "$expected_response" "$filtered_response" "update_user"
        rm -f "$output_bin_name"
        rm -f "$user_bin_file"
    else
        filtered_response=$(echo "$curl_response" | jq 'del(.createdAt, .updatedAt)') || filtered_response="$curl_response"
        assert_equal_file "$expected_response" "$filtered_response" "update_user"
    fi
}
test_delete_user(){
    local bearer_token="$1"
    local url="$2"
    local uri="$3"
    output_bin_name="${SCRIPT_DIR}/temp_$(date +%s%N).bin"
    local curl_extras=()
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        curl_extras+=(--output "$output_bin_name")
    else
        local user_data=$(cat "$user_data_file")
    fi
    
    local curl_request=$(curl --location --globoff --request DELETE "$url/$uri" \
        --header 'Accept: */*' \
        --header "Authorization: Bearer $bearer_token" \
        "${curl_extras[@]}" \
    )
    
    local curl_response=$(echo "$curl_request")
    echo "$(cat $output_bin_name)"
    
    local expected_response_file="./test/JSON/delete_user_expected.json"
    if [ ! -f "$expected_response_file" ]; then
        echo "Error: Expected response file '$expected_response_file' does not exist."
        echo "delete_user assertion failed. 🟥"
        return 1
    fi
    
    local filtered_response
    local expected_response=$(cat "$expected_response_file")
    
    if [ $IS_TESTING_PROTOBUF -eq 1 ]; then
        local type="UserResponseDTO"
        json=$(parse_proto_to_json "$type" "$PROTOS" "$output_bin_name")
        filtered_response=$(echo "$json" | jq 'del(.createdAt, .updatedAt)')
        assert_equal_file "$expected_response" "$filtered_response" "delete_user"
        rm -f "$output_bin_name"
    else
        filtered_response=$(echo "$curl_response" | jq 'del(.createdAt, .updatedAt)') || filtered_response="$curl_response"
        assert_equal_file "$expected_response" "$filtered_response" "delete_user"
    fi
}

# test_get_all_product_entities "$BEARER_TOKEN" "$BASE_URL" "products/entities?orderby=id&order=asc"
test_get_user_by_id "$BEARER_TOKEN" "$BASE_URL" "users/1" #! WORKS
# test_get_all_users "$BEARER_TOKEN" "$BASE_URL" "users?orderby=id&order=asc"
test_create_user "$BEARER_TOKEN" "$BASE_URL" "users" #! WORKS
test_update_user "$BEARER_TOKEN" "$BASE_URL" "users/401" #! WORKS
test_delete_user "$BEARER_TOKEN" "$BASE_URL" "users/401" #! WORKS

