-- execute this script while connected to your new database

--1. drop tables
DROP TABLE IF EXISTS customer;



--2 create all tables
CREATE TABLE customer
(
        customer_id SERIAL NOT NULL PRIMARY KEY
        , first_name VARCHAR(50) NOT NULL
        , last_name VARCHAR(50) NOT NULL
);

CREATE TABLE sale
(
        sale_id SERIAL NOT NULL PRIMARY KEY
        , customer_id INTEGER NOT NULL
        , purchase_date DATE NOT NULL
);


-- 3. add constraints

-- foriegn keys
ALTER TABLE sale 
    ADD CONSTRAINT fk_customer_sale 
        FOREIGN KEY (customer_id) 
            REFERENCES customer (customer_id); 

-- creating this constraint would make it impossible to back-date a purchase though
ALTER TABLE sale
    ADD CONSTRAINT ck_sale_purchase_date
        CHECK (purchase_date >= now()); -- or CURRENT_DATE



