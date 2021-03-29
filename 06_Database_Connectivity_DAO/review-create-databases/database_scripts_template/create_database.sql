-- DDL - Data Definition Language
-- CREATE database, table
-- DROP database, table
-- ALTER table

-- connected to the POSTGRES database when you are creating another db

-- kill and db locks
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'gills_art';

DROP DATABASE gills_art;

CREATE DATABASE gills_art;
