-- execute this while connected to the gills_art database

--1. drop all tables
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS sale_line_item;
DROP TABLE IF EXISTS art;
DROP TABLE IF EXISTS artist;


--2 create all tables
CREATE TABLE customer
(
        -- column_name datatype NULL/NOT NULL constraints
        customer_id SERIAL NOT NULL PRIMARY KEY
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
        sale_id SERIAL NOT NULL
        , customer_id INTEGER NOT NULL
        , purchase_date DATE NOT NULL
        , PRIMARY KEY (
                sale_id
        )  
);

CREATE TABLE sale_line_item
(
        sale_id INTEGER NOT NULL
        , art_id INTEGER NOT NULL
        , price DECIMAL(10,2) NOT NULL
        
        , PRIMARY KEY (
                sale_id
                , art_id
        )
);

CREATE TABLE art
(
        art_id SERIAL NOT NULL PRIMARY KEY
        , artist_id INTEGER NOT NULL
        , title VARCHAR(255) NOT NULL
);

CREATE TABLE artist
(
        artist_id INTEGER NOT NULL PRIMARY KEY
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


-- insert data

-- customer
INSERT INTO customer (first_name, last_name, street_address, city, state, zip, phone) VALUEs ('Elizabeth', 'Jackson', '123 - 4th Avenue', 'Fonthill', 'ON', 'L3J 4S4', '(260)284-6783');

-- artist
INSERT INTO artist (artist_id, name) VALUES (3, 'Carol Channing');
INSERT INTO artist (artist_id, name) VALUES (15, 'Dennis Frings');

-- art
INSERT INTO art (artist_id, title) VALUES (3, 'Laugh with Teeth');
INSERT INTO art (artist_id, title) VALUES (15, 'South toward Emerald Sea');
INSERT INTO art (artist_id, title) VALUES (3, 'At the Movies');

-- sale
INSERT INTO sale (customer_id, purchase_date) VALUES (1, '9/17/2000');
INSERT INTO sale (customer_id, purchase_date) VALUES (1, '5/11/2000');
INSERT INTO sale (customer_id, purchase_date) VALUES (1, '2/14/2002');
INSERT INTO sale (customer_id, purchase_date) VALUES (1, '7/15/2003');

-- sale_line_item
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (1,1,7000);
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (2,2,1800);
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (3,3,5550);
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (4,2,2200);
