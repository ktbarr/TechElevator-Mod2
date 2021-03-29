-- connected to the SYSTEM (postgres) database when you are creating another db

-- kill any processes that have a lock on the database
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'gills_art';

-- drop the database
DROP DATABASE IF EXISTS gills_art;

-- create the database
CREATE DATABASE gills_art;
