-- execute this script while connected to ther gills_art database

-- customer
INSERT INTO customer (customer_id, first_name, last_name, street_address, city, state, zip, phone) VALUEs (1, 'Elizabeth', 'Jackson', '123 - 4th Avenue', 'Fonthill', 'ON', 'L3J 4S4', '(260)284-6783');

ALTER SEQUENCE customer_id_sequence RESTART WITH 2;


-- artist
INSERT INTO artist (artist_id, name) VALUES (3, 'Carol Channing');
INSERT INTO artist (artist_id, name) VALUES (15, 'Dennis Frings');

ALTER SEQUENCE artist_id_sequence RESTART WITH 16;


-- art
INSERT INTO art (art_id, artist_id, title) VALUES (1, 3, 'Laugh with Teeth');
INSERT INTO art (art_id, artist_id, title) VALUES (2, 15, 'South toward Emerald Sea');
INSERT INTO art (art_id, artist_id, title) VALUES (3, 3, 'At the Movies');

ALTER SEQUENCE art_id_sequence RESTART WITH 4;


-- sale
INSERT INTO sale (customer_id, purchase_date) VALUES (1, 1, '9/17/2000');
INSERT INTO sale (customer_id, purchase_date) VALUES (2, 1, '5/11/2000');
INSERT INTO sale (customer_id, purchase_date) VALUES (3, 1, '2/14/2002');
INSERT INTO sale (customer_id, purchase_date) VALUES (4, 1, '7/15/2003');

ALTER SEQUENCE sale_id_sequence RESTART WITH 4;


-- sale_line_item
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (1,1,7000);
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (2,2,1800);
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (3,3,5550);
INSERT INTO sale_line_item (sale_id, art_id, price) VALUES (4,2,2200);