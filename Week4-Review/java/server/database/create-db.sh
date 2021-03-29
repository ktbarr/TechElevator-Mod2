#!/bin/bash
export PGPASSWORD='postgres1'

psql -U postgres -f "./createdb.sql" &&
psql -U postgres -d vendingmachine -f "./vendingmachine.sql" 