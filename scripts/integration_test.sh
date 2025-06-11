#!/bin/bash

IS_TESTING_PROTOBUF=0

if [ -z "$1" ]; then
    echo "Error: No serialisation specified"
    echo "Usage: $0 <serialisation>"
    echo "       <serialisation> : Serialisation to test (either JSON or Protocol Buffers)"
    exit 1
fi

if [[ "$test_name" == *"protobuf"* ]]; then
    IS_TESTING_PROTOBUF=1
fi

PORT=8080
if [ IS_TESTING_PROTOBUF -eq 1]; then
    PORT=8081
fi
BASE_URL="http://localhost:$PORT/"
ENDPOINTS=(
    "products/entities?orderby=id&order=asc:GET"
    "users/{id}:GET"
    "users?orderby=id&order=asc:GET"
    "users:POST"
    "users/{id}:PUT"
    "users/{id}:DELETE"

)

POST_USER=(
	"username:SIMPLEUSER"
	"email:simpleUser@gmail.com"
	"password:123456789"
	"role:EMPLOYEE"
	)

PUT_USER=(
	"username:NEWUSERNAME"
	"email:simpleUser@gmail.com"
	)

