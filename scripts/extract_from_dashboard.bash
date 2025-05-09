#!/bin/bash

GRAFANA_URL="http://localhost:3000"
PANEL_ID="16"

if ! command -v jq &> /dev/null; then
    echo "Error: jq is not installed but required for JSON processing."
    echo ""
    echo "Please install jq using one of the following methods:"
    echo ""
    echo "For Windows (using chocolatey):"
    echo "  choco install jq"
    echo ""
    echo "For Windows (manual download):"
    echo "  Download from: https://stedolan.github.io/jq/download/"
    echo ""
    echo "For macOS:"
    echo "  brew install jq"
    echo ""
    echo "For Linux:"
    echo "  sudo apt-get install jq  # Debian/Ubuntu"
    echo "  sudo dnf install jq      # Fedora"
    echo "  sudo yum install jq      # CentOS/RHEL"
    echo ""
    exit 1
fi

usage(){
  echo "Usage: $0 -s <start_time> -e <end_time> -t <api_token> -d <dashboard_uid> [-u <grafana_url>] [-p <panel_id>]"
  echo "  -s <start_time>   Start time in ISO format (e.g., '2023-05-09T16:55:00')"
  echo "  -e <end_time>     End time in ISO format (e.g., '2023-05-09T17:05:00')"
  echo "  -t <api_token>    Grafana API token (required)"
  echo "  -d <dashboard_uid> Dashboard UID (required)"
  echo "  -u <grafana_url>  Grafana URL (default: $GRAFANA_URL)"
  echo "  -p <panel_id>     Panel ID to extract data from (default: $PANEL_ID)"
  echo ""
  echo "Example:"
  echo "  $0 -s '2023-05-09T16:55:00' -e '2023-05-09T17:05:00' -t 'glsa_abc123...' -d 'k8s_cluster_overview'"
  exit 1
}
START_TIME=""
END_TIME=""
API_TOKEN=""
DASHBOARD_UID=""

while getopts "s:e:t:d:u:p:" opt; do
  case ${opt} in
    s )
      START_TIME=$OPTARG
      ;;
    e )
      END_TIME=$OPTARG
      ;;
    t )
      API_TOKEN=$OPTARG
      ;;
    d )
      DASHBOARD_UID=$OPTARG
      ;;
    u )
      GRAFANA_URL=$OPTARG
      ;;
    p )
      PANEL_ID=$OPTARG
      ;;
    \? )
      usage
      ;;
  esac
done

if [[ -z "$START_TIME" || -z "$END_TIME" || -z "$API_TOKEN" || -z "$DASHBOARD_UID" ]]; then
  echo "Error: Missing required parameters."
  usage
fi

if [[ "$START_TIME" =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2} ]]; then
  START_TIME=$(date -d "$START_TIME" +%s%3N)
fi

if [[ "$END_TIME" =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2} ]]; then
  END_TIME=$(date -d "$END_TIME" +%s%3N)
fi

echo "Fetching data from Grafana for time range $START_TIME to $END_TIME..."


echo "Getting Prometheus datasource ID..."
DATASOURCES=$(curl -s -X GET \
  "$GRAFANA_URL/api/datasources" \
  -H "Authorization: Bearer $API_TOKEN")

PROMETHEUS_DS_ID=$(echo "$DATASOURCES" | jq '.[] | select(.type=="prometheus") | .id' | head -1)


if [[ -z "$PROMETHEUS_DS_ID" ]]; then
  echo "Error: Could not find Prometheus datasource."
  exit 1
fi

echo "Found Prometheus datasource ID: $PROMETHEUS_DS_ID"

echo "Getting dashboard JSON..."
DASHBOARD_JSON=$(curl -s -X GET \
  "$GRAFANA_URL/api/dashboards/uid/$DASHBOARD_UID" \
  -H "Authorization: Bearer $API_TOKEN")

if echo "$DASHBOARD_JSON" | jq -e '.message == "Dashboard not found"' &>/dev/null; then
  echo "Error: Dashboard with UID '$DASHBOARD_UID' not found."
  exit 1
fi

PANEL_INFO=$(echo "$DASHBOARD_JSON" | jq ".dashboard.panels[] | select(.id == $PANEL_ID)")


if [[ -z "$PANEL_INFO" ]]; then
  echo "Error: Panel with ID $PANEL_ID not found in dashboard."
  echo "Available panels:"
  echo "$DASHBOARD_JSON" | jq -r '.dashboard.panels[] | "ID: \(.id), Title: \(.title)"'
  exit 1
fi



PANEL_TITLE=$(echo "$PANEL_INFO" | jq -r '.title')
QUERY_EXPR=$(echo "$PANEL_INFO" | jq -r '.targets[0].expr')

if [[ -z "$QUERY_EXPR" ]]; then
  echo "Error: Could not find query expression for panel ID $PANEL_ID"
  exit 1
fi

echo "Found panel: $PANEL_TITLE"
echo "Query: $QUERY_EXPR"


echo "Fetching metric data..."
RESPONSE=$(curl -s -X GET \
  "$GRAFANA_URL/api/datasources/proxy/$PROMETHEUS_DS_ID/api/v1/query_range" \
  -H "Authorization: Bearer $API_TOKEN" \
  -H "Content-Type: application/json" \
  --data-urlencode "query=$QUERY_EXPR" \
  --data-urlencode "start=$START_TIME" \
  --data-urlencode "end=$END_TIME" \
  --data-urlencode "step=1s")

SAFE_TITLE=$(echo "$PANEL_TITLE" | tr ' /' '_')
OUTPUT_FILE="${SAFE_TITLE}_$(date +%Y%m%d%H%M%S).csv"

echo "Processing data..."
echo "timestamp,value,series" > "$OUTPUT_FILE"

RESULT_COUNT=$(echo "$RESPONSE" | jq '.data.result | length')
if [[ "$RESULT_COUNT" -eq 0 ]]; then
  echo "Warning: No data points found in the specified time range."
  echo "$RESPONSE" | jq '.'
fi

echo "$RESPONSE" | jq -r '.data.result[] | 
  .metric as $metric | 
  .values[] | 
  [.[0], .[1], ($metric | to_entries | map("\(.key)=\(.value)") | join(","))] | 
  join(",")' >> "$OUTPUT_FILE"

# Convert timestamps to human-readable format
echo "Converting timestamps..."
TMP_FILE="${OUTPUT_FILE}.tmp"
head -1 "$OUTPUT_FILE" > "$TMP_FILE"
tail -n +2 "$OUTPUT_FILE" | while IFS=',' read -r timestamp value series; do
  human_time=$(date -d @$timestamp "+%Y-%m-%d %H:%M:%S")
  echo "$human_time,$value,$series" >> "$TMP_FILE"
done
mv "$TMP_FILE" "$OUTPUT_FILE"

echo "Data extraction complete. Results saved to $OUTPUT_FILE"
