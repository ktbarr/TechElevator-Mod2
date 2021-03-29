-- execute this script while connected to ther gills_art database

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