export PGPASSWORD='postgres1'

psql -U postgres -f "./create_database.sql" &&
psql -U postgres -d gills_art -f "./create_schema.sql" &&
psql -U postgres -d gills_art -f "./create_seed_data.sql" 
