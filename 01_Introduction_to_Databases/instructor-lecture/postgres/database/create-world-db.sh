#!/bin/bash
psql -U postgres -f "./dropdb.sql" &&
createdb -U postgres world &&
psql -U postgres -d world -f "./world.sql" 