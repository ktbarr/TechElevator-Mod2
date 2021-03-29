
CREATE TABLE product_type
(
    product_type_id SERIAL NOT NULL PRIMARY KEY ,
    name varchar(10) NOT NULL
);

CREATE TABLE product (
    product_id SERIAL NOT NULL PRIMARY KEY,
    name varchar(30) NOT NULL,
    price money NOT NULL,
    product_type_id integer NOT NULL
) ;

CREATE TABLE inventory (
    slot_id char(2) NOT NULL PRIMARY KEY,
    product_id integer NOT NULL,
    quantity integer NOT NULL 
);

CREATE TABLE log (
    log_id SERIAL NOT NULL PRIMARY KEY ,
    log_date date NOT NULL,
    action varchar(30) NOT NULL,
    start_credit money NOT NULL,
    end_credit money NOT NULL
);

CREATE TABLE users (
	user_id SERIAL NOT NULL PRIMARY KEY,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL
    balance money NOT NULL 
);

INSERT INTO product_type (name) VALUES ('Chip');
INSERT INTO product_type (name) VALUES ('Candy');
INSERT INTO product_type (name) VALUES ('Drink');
INSERT INTO product_type (name) VALUES ('Gum');


INSERT INTO product (name, price, product_type_id) VALUES ('Potato Crisps','3.05',1);
INSERT INTO product (name, price, product_type_id) VALUES ('Stackers','1.45',1);
INSERT INTO product (name, price, product_type_id) VALUES ('Grain Waves','2.75',1);
INSERT INTO product (name, price, product_type_id) VALUES ('Cloud Popcorn','3.65',1);
INSERT INTO product (name, price, product_type_id) VALUES ('Moonpie','1.80',2);
INSERT INTO product (name, price, product_type_id) VALUES ('Cowtales','1.50',2);
INSERT INTO product (name, price, product_type_id) VALUES ('Wonka Bar','1.50',2);
INSERT INTO product (name, price, product_type_id) VALUES ('Crunchie','1.75',2);
INSERT INTO product (name, price, product_type_id) VALUES ('Cola','1.25',3);
INSERT INTO product (name, price, product_type_id) VALUES ('Dr. Salt','1.50',3);
INSERT INTO product (name, price, product_type_id) VALUES ('Mountain Melter','1.50',3);
INSERT INTO product (name, price, product_type_id) VALUES ('Heavy','1.50',3);
INSERT INTO product (name, price, product_type_id) VALUES ('U-Chews','0.85',4);
INSERT INTO product (name, price, product_type_id) VALUES ('Little League Chew','0.95',4);
INSERT INTO product (name, price, product_type_id) VALUES ('Chiclets','0.75',4);
INSERT INTO product (name, price, product_type_id) VALUES ('Triplemint','0.75',4);


INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('A1',1,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('A2',2,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('A3',3,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('A4',4,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('B1',5,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('B2',6,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('B3',7,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('B4',8,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('C1',9,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('C2',10,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('C3',11,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('C4',12,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('D1',13,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('D2',14,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('D3',15,5);
INSERT INTO  inventory (slot_id, product_id, quantity) VALUES ('D4',16,5);

INSERT INTO users (username,password_hash, balance) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 0);
