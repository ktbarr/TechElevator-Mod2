-- DDL - data definition language
-- CREATE database, table,
-- DROP database, table
-- ALTER table

-- this is the more common method to DROP, basically clearing the whole database, then recreating DB then table.

-- connected to the postgres database when you are creating another db

-- kills locks/connections
SELECT pg_terminate_backend(pid) FROM pg_start_activity WHERE datname = 'gills_art';

-- removes database
DROP DATABASE gills_art;

-- creates database
CREATE DATABASE gills_art;
