#!/bin/bash


SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# Decode the protobuf binary into text format
DECODED_TEXT=$(protoc --decode="$1" --proto_path="$SCRIPT_DIR" "$2" < "$3")

# Process the decoded text into JSON
IS_SIMPLE_LIST=$4
if [[ "$IS_SIMPLE_LIST" == "false" ]]; then
    echo "$DECODED_TEXT" | awk '
    BEGIN {
        print "{";
        indent = "    ";
        level = 0;
        first[level] = 1;
        in_array = 0;
    }

    function close_until(target) {
        while (level > target) {
            if (in_array && level == array_level) {
                printf "\n%s]", array_indent;
                in_array = 0;
            } else {
                printf "\n%s}", indent;
            }
            level--;
            indent = substr(indent, 1, length(indent)-4);
            first[level] = 0;
        }
    }

    function print_comma() {
        if (!first[level]) printf ",";
        printf "\n";
        first[level] = 0;
    }

    # Skip empty lines and date fields
    /^$/ { next; }
    /created_at/ { next; }
    /updated_at/ { next; }
    /^[[:space:]]+(year|month|day)/ { next; }

    # Handle nested messages (indented blocks)
    /^[[:space:]]/ {
        # Count indentation
        spaces = 0;
        while (substr($0, spaces+1, 1) == " ") spaces++;
        current_level = spaces / 2;

        # Close deeper levels if needed
        if (current_level < level) {
            close_until(current_level);
        }

        # If we have content after indentation
        sub(/^[[:space:]]+/, "");

        # If this is a new object (not a key:value)
        if ($0 !~ /:/ && $0 !~ /^{/ && $0 !~ /^}/) {
            if (!first[level]) print_comma();
            printf "%s\"%s\": {", indent, $0;
            level++;
            indent = indent "    ";
            first[level] = 1;
        }
    }

    # Handle top-level fields
    /^[^[:space:]]/ {
        # Close deeper levels if we'\''re starting a new top-level field
        if (level > 0 && !in_array) {
            close_until(0);
        }

        # Handle repeated fields (arrays)
        if (prev_key == $1) {
            if (!in_array) {
                # Start array
                print_comma();
                printf "%s\"%s\": [\n%s{", indent, $1, indent "    ";
                array_level = level;
                array_indent = indent "    ";
                in_array = 1;
                level++;
                indent = array_indent "    ";
                first[level] = 1;
            } else {
                # Next array element
                printf "\n%s},\n%s{", array_indent, array_indent;
                first[level] = 1;
            }
        } else if (in_array) {
            # End array
            printf "\n%s}\n%s]", array_indent, indent;
            in_array = 0;
            level = array_level;
            indent = substr(indent, 1, length(indent)-8);
        }
    }

    # Extract key-value pairs
    /:/ {
        key = $1;
        sub(/:$/, "", key);
        value = substr($0, index($0, $2));
        sub(/;$/, "", value);  # Remove trailing semicolons

        if (!first[level]) print_comma();

        # Quote strings, leave numbers/booleans/enums as-is
        if (value ~ /^"/) {
            printf "%s\"%s\": %s", indent, key, value;
        } else if (value ~ /^[0-9]+\.[0-9]+/) {
            printf "%s\"%s\": %s", indent, key, value;
        } else if (value ~ /^[0-9]+/) {
            printf "%s\"%s\": %s", indent, key, value;
        } else if (value ~ /^(true|false)$/) {
            printf "%s\"%s\": %s", indent, key, value;
        } else {
            printf "%s\"%s\": \"%s\"", indent, key, value;
        }

        prev_key = key;
        first[level] = 0;
    }

    END {
        close_until(0);
        if (in_array) {
            printf "\n%s]", array_indent;
        }
        print "\n}";
    }
    '
else
    awk -v first_user=1 -v skip_block=0 '
BEGIN {
    print "["
}
/^users {/ {
    if (!first_user) {
        print "  },"
    }
    first_user = 0
    print "  {"
    first_field = 1
    next
}
/^}/ {
    if (skip_block) {
        skip_block = 0
        next
    }
    next
}
/created_at {/ || /updated_at {/ {
    skip_block = 1
    next
}
skip_block {
    next
}
/: / {
    if (!first_field) {
        printf ",\n"
    } else {
        first_field = 0
    }
    
    # Remove the colon from the key
    key = $1
    sub(/:$/, "", key)
    $1 = ""
    value = $0
    sub(/^[ \t:]+/, "", value)
    
    # Remove quotes if present
    if (value ~ /^".*"$/) {
        value = substr(value, 2, length(value)-2)
    }
    
    # Special handling for id (no quotes)
    if (key == "id") {
        printf "    \"%s\": %s", key, value
    }
    # Keep ADMIN/EMPLOYEE in uppercase
    else if (value == "ADMIN" || value == "EMPLOYEE") {
        printf "    \"%s\": \"%s\"", key, value
    }
    # Regular string fields
    else {
        printf "    \"%s\": \"%s\"", key, value
    }
    next
}
END {
    if (!first_user) {
        print "\n  }"
    }
    print "]"
}' <<< "$DECODED_TEXT"
fi