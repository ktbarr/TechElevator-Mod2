-- create TABLES
BEGIN TRANSACTION;

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
	password_hash varchar(200) NOT NULL,
    balance money NOT NULL
);

CREATE TABLE roles (
    role_id SERIAL NOT NULL PRIMARY KEY,
    rolename varchar(20) NOT NULL
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (
        user_id,
        role_id
    )
);

-- add relationships

ALTER TABLE product
ADD CONSTRAINT fk_product_product_type
FOREIGN KEY (product_type_id)
    REFERENCES product_type(product_type_id);

ALTER TABLE inventory
ADD CONSTRAINT fk_inventory_product
FOREIGN KEY (product_id)
    REFERENCES product(product_id);

ALTER TABLE user_roles
ADD CONSTRAINT fk_user_roles_users
FOREIGN KEY (user_id)
    REFERENCES users(user_id);

ALTER TABLE user_roles
ADD CONSTRAINT fk_user_roles_roles
FOREIGN KEY (role_id)
    REFERENCES roles(role_id);

-- add seed data

INSERT INTO product_type (name) 
VALUES ('Chip')
    , ('Candy')
    , ('Drink')
    , ('Gum');


INSERT INTO product (name, price, product_type_id) 
VALUES ('Potato Crisps','3.05',1)
    , ('Stackers','1.45',1)
    , ('Grain Waves','2.75',1)
    , ('Cloud Popcorn','3.65',1)
    , ('Moonpie','1.80',2)
    , ('Cowtales','1.50',2)
    , ('Wonka Bar','1.50',2)
    , ('Crunchie','1.75',2)
    , ('Cola','1.25',3)
    , ('Dr. Salt','1.50',3)
    , ('Mountain Melter','1.50',3)
    , ('Heavy','1.50',3)
    , ('U-Chews','0.85',4)
    , ('Little League Chew','0.95',4)
    , ('Chiclets','0.75',4)
    , ('Triplemint','0.75',4);


INSERT INTO  inventory (slot_id, product_id, quantity) 
VALUES ('A1',1,5)
    , ('A2',2,5)
    , ('A3',3,5)
    , ('A4',4,5)
    , ('B1',5,5)
    , ('B2',6,5)
    , ('B3',7,5)
    , ('B4',8,5)
    , ('C1',9,5)
    , ('C2',10,5)
    , ('C3',11,5)
    , ('C4',12,5)
    , ('D1',13,5)
    , ('D2',14,5)
    , ('D3',15,5)
    , ('D4',16,5);

INSERT INTO roles(rolename) VALUES ('USER')
                            , ('ADMIN')
                            , ('VENDOR');

INSERT INTO users (username,password_hash, balance) 
VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 0)
    , ('admin','$2a$10$qiy5CUYfu1Ydr304Er/V3OBdQP34lB8ZOAZbC7dtnizkrfhpvXXJq', 0)
    , ('vendor','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 0);

INSERT INTO user_roles (user_id, role_id)
VALUES(1,1)
    , (2,1)
    , (2,2)
    , (3,3);

COMMIT;