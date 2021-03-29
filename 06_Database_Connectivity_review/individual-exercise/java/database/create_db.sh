export PGPASSWORD='postgres1'

psql -U postgres -f "./drop_and_create_projects_database.sql" &&
psql -U postgres -d projects -f "./create_projects_tables.sql" 