#!/bin/bash
CONTAINER_NAME=e-commerce-db-1
docker exec -it $CONTAINER_NAME psql -U postgres -d postgres \
    -c "CREATE database smartdriving" \
    -c "CREATE USER postgres WITH ENCRYPTED PASSWORD 'postgres';" \
    -c "GRANT ALL PRIVILEGES ON DATABASE smartdriving TO postgres;" \
    -c "\l" \
    /