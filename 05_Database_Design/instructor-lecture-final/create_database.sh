
export PGPASSWORD='postgres1'

psql -U postgres -f "./createdb.sql" &&
psql -U postgres -d gills_art -f "./lecture.sql" 