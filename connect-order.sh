source .env

PGPASSWORD=$PGSQL_PASSWORD psql -h $PGSQL_HOST -U $PGSQL_USER -d $ORDER_PGSQL_DATABASE
