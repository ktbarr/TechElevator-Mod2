#!/bin/bash
export PGPASSWORD='postgres1'

psql -U postgres -f "./dropdb.sql" &&
createdb -U postgres dvdstore &&
psql -U postgres -d dvdstore -f "./dvdstore.sql" 