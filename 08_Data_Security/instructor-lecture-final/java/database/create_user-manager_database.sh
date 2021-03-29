# this sets a default value for the  postgres password
# so that when you run the following postgres commands
# in this script - this password will be used to execute 
export PGPASSWORD='postgres1'

# this executes the sql script to both DROP and CREATE the user-manager database
# it connects to the postgres (system) database to execute this script. 
# That is specified by the "-d postgres" flag
psql -U postgres -d postgres -f "./create_user-manager_database.sql"

# this line of code executes the sql script to create all of the tables
# for the database. It connects to the user-manager database to run the script.
# That is specified by the "-d user-manager" flag
psql -U postgres -d user-manager -f "./create_user-manager_tables.sql"
