#!/bin/sh
# Dumps the production database into a snapshot for open source development. Yay.
#
# Usage:
# ./prod-db-dump.sh [host] [username] [dbname]

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
current_timestamp=$(date +%Y%m%d_%H%M%S)
pg_dump \
    -f="./${DIR}/../../snapshots/${current_timestamp}.sql" \
    --exclude-table=account_oauth \
    --exclude-table=account_email \
    --exclude-table=account_link_request \
    --exclude-table=password_change_request \
    --exclude-table=cellar \
    --compress=9 \
    --host=$1 \
    --username=$2 \
    $3
