-- The following line terminates any active connections to the database so that it can be dropped
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'vendingmachine';

DROP DATABASE IF EXISTS "vendingmachine";

CREATE DATABASE "vendingmachine";