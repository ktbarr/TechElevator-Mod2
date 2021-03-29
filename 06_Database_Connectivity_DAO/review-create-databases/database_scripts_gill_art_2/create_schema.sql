-- execute this while connected to the gills_art database

--1. drop all tables
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS sale_line_item;
DROP TABLE IF EXISTS art;
DROP TABLE IF EXISTS artist;


-- 2 create autonumber sequences
CREATE SEQUENCE IF NOT EXISTS customer_id_sequence;
CREATE SEQUENCE IF NOT EXISTS artist_id_sequence;
CREATE SEQUENCE IF NOT EXISTS art_id_sequence;
CREATE SEQUENCE IF NOT EXISTS sale_id_sequence;


--3 create all tables
CREATE TABLE customer
(
        -- column_name datatype NULL/NOT NULL constraints
        customer_id INT NOT NULL PRIMARY KEY DEFAULT nextval('customer_id_sequence')
        , first_name VARCHAR(50) NOT NULL
        , last_name VARCHAR(50) NOT NULL
        , street_address VARCHAR(50) NULL
        , city VARCHAR(20) NULL
        , state CHAR(2) NULL
        , zip VARCHAR(10) NULL
        , phone VARCHAR(15) NOT NULL
);

CREATE TABLE sale
(
        sale_id INT NOT NULL PRIMARY KEY DEFAULT nextval('sale_id_sequence')
        , customer_id INTEGER NOT NULL
        , purchase_date DATE NOT NULL
);

CREATE TABLE sale_line_item
(
        sale_id INT NOT NULL
        , art_id INT NOT NULL
        , price DECIMAL(10,2) NOT NULL
        
        , PRIMARY KEY (
                sale_id
                , art_id
        )
);

CREATE TABLE art
(
        art_id INT NOT NULL PRIMARY KEY DEFAULT nextval('art_id_sequence')
        , artist_id INTEGER NOT NULL
        , title VARCHAR(255) NOT NULL
);

CREATE TABLE artist
(
        artist_id INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('artist_id_sequence')
        , name VARCHAR(100)
);

-- 3. add relationships

ALTER TABLE sale -- which table are we altering
ADD CONSTRAINT fk_customer_sale -- name of the constraint
FOREIGN KEY (customer_id) -- which column in the sale table
REFERENCES customer (customer_id); -- point to other table and column

ALTER TABLE sale_line_item
ADD CONSTRAINT fk_sale_sale_line_item
FOREIGN KEY (sale_id)
REFERENCES  sale (sale_id);

ALTER TABLE sale_line_item
ADD CONSTRAINT fk_art_sale_line_item
FOREIGN KEY (art_id)
REFERENCES  art (art_id);

ALTER TABLE art
ADD CONSTRAINT fk_artist_art
FOREIGN KEY (artist_id)
REFERENCES  artist (artist_id);

ALTER TABLE sale_line_item
ADD CONSTRAINT ck_sale_line_item_price
CHECK (price > 0);


