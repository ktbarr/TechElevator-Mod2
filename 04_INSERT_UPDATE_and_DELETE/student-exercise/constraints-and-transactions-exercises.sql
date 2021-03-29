-- Write queries to return the following:
-- The following changes are applied to the "dvdstore" database.**

-- 1. Add actors, Hampton Avenue, and Lisa Byway to the actor table.

SELECT *
FROM actor
ORDER BY last_name;

BEGIN TRANSACTION;

INSERT INTO actor(first_name, last_name)
VALUES ('HAMPTON', 'AVENUE');

INSERT INTO actor(first_name, last_name)
VALUES ('LISA', 'BYWAY');

SELECT actor_id
        , first_name
        , last_name
FROM actor
WHERE actor.last_name = 'AVENUE' OR actor.last_name = 'BYWAY';

COMMIT TRANSACTION;

-- ROLLBACK TRANSACTION; not needed, SELECT check of data was successful

-- 2. Add "Euclidean PI", "The epic story of Euclid as a pizza delivery boy in
-- ancient Greece", to the film table. The movie was released in 2008 in English.
-- Since its an epic, the run length is 3hrs and 18mins. There are no special
-- features, the film speaks for itself, and doesn't need any gimmicks.

SELECT *
FROM film;

SELECT *
FROM language;

BEGIN TRANSACTION;

INSERT INTO film(title, description, release_year, language_id, length)
VALUES('EUCLIDEAN PI', 'The epic story of Euclid as a pizza delivery boy in ancient Greece', 2008, 1, 198);

SELECT *
FROM film
WHERE title = 'EUCLIDEAN PI';

COMMIT TRANSACTION;

-- ROLLBACK TRANSACTION; not needed, SELECT data check was successful

-- 3. Hampton Avenue plays Euclid, while Lisa Byway plays his slightly
-- overprotective mother, in the film, "Euclidean PI". Add them to the film.

-- film_id = 1001
-- Hampton Avenue actor_id = 201
-- Lisa Byway actor_id = 202

SELECT *
FROM film_actor;

SELECT actor_id
        , first_name
        , last_name
FROM actor
WHERE actor.last_name = 'AVENUE' OR actor.last_name = 'BYWAY';

BEGIN TRANSACTION;

INSERT INTO film_actor(film_id, actor_id)
VALUES(1001, 201);

INSERT INTO film_actor(film_id, actor_id)
VALUES(1001, 202);

SELECT film_id 
        , actor_id
FROM film_actor
WHERE film_id = 1001;

COMMIT TRANSACTION;

-- ROLLBACK TRANSACTION; not needed, SELECT data check was successful

-- 4. Add Mathmagical to the category table.

SELECT *
FROM category;

BEGIN TRANSACTION;

INSERT INTO category(category_id, name)
VALUES(17, 'Mathmagical');

SELECT *
FROM category
WHERE category_id = 17;

COMMIT TRANSACTION;

-- 5. Assign the Mathmagical category to the following films, "Euclidean PI",
-- "EGG IGBY", "KARATE MOON", "RANDOM GO", and "YOUNG LANGUAGE"

-- category_id of Mathmagical = 17
-- film_ids = 'EUCLIDEAN PI'(1001) 'EGG IGBY'(274) 'KARATE MOON'(494) 'RANDOM GO'(714) 'YOUNG LANGUAGE'(996)
-- OG category_ids = 'EUCLIDEAN PI'(NULL) 'EGG IGBY'(6) 'KARATE MOON'(11) 'RANDOM GO'(14) 'YOUNG LANGUAGE'(6)

SELECT film_id
        , title
FROM film
WHERE title = 'EUCLIDEAN PI' 
    OR title = 'EGG IGBY'
    OR title = 'KARATE MOON' 
    OR title = 'RANDOM GO' 
    OR title = 'YOUNG LANGUAGE'; 

SELECT film_id
        , category_id
FROM film_category
WHERE film_id = 1001 
    OR film_id = 274 
    OR film_id = 494 
    OR film_id = 714 
    OR film_id = 996;  

BEGIN TRANSACTION;

INSERT INTO film_category(film_id, category_id)
VALUES(1001, 17);

UPDATE film_category
SET category_id = 17
WHERE film_id = 274;

UPDATE film_category
SET category_id = 17
WHERE film_id = 494;

UPDATE film_category
SET category_id = 17
WHERE film_id = 714;

UPDATE film_category
SET category_id = 17
WHERE film_id = 996;

SELECT film_id
        , category_id
FROM film_category
WHERE film_id = 1001 
    OR film_id = 274 
    OR film_id = 494 
    OR film_id = 714 
    OR film_id = 996;

COMMIT TRANSACTION; 


-- 6. Mathmagical films always have a "G" rating, adjust all Mathmagical films
-- accordingly.
-- (5 rows affected)
-- OG ratings = 'EUCLIDEAN PI'(G) 'EGG IGBY'(PG) 'KARATE MOON'(PG-13) 'RANDOM GO'(NC-17) 'YOUNG LANGUAGE'(G)

SELECT film_id
        , rating
FROM film
WHERE film_id = 1001 
    OR film_id = 274 
    OR film_id = 494 
    OR film_id = 714 
    OR film_id = 996;

BEGIN TRANSACTION;

UPDATE film
SET rating = 'G'
WHERE film_id = 1001;

UPDATE film
SET rating = 'G'
WHERE film_id = 274;

UPDATE film
SET rating = 'G'
WHERE film_id = 494;

UPDATE film
SET rating = 'G'
WHERE film_id = 714;

UPDATE film
SET rating = 'G'
WHERE film_id = 996;

SELECT film_id
        , rating
FROM film
WHERE film_id = 1001 
    OR film_id = 274 
    OR film_id = 494 
    OR film_id = 714 
    OR film_id = 996;

COMMIT TRANSACTION;

-- 7. Add a copy of "Euclidean PI" to all the stores.

SELECT *
FROM inventory
ORDER BY inventory_id DESC;

SELECT *
FROM store;

BEGIN TRANSACTION;

INSERT INTO inventory(inventory_id, film_id, store_id)
VALUES(4582, 1001, 1);

INSERT INTO inventory(inventory_id, film_id, store_id)
VALUES(4583, 1001, 2);

COMMIT TRANSACTION;

-- 8. The Feds have stepped in and have impounded all copies of the pirated film,
-- "Euclidean PI". The film has been seized from all stores, and needs to be
-- deleted from the film table. Delete "Euclidean PI" from the film table.

SELECT *
FROM film
WHERE film_id = 1001;

DELETE FROM film
WHERE film_id = 1001;

-- (Did it succeed? Why?)
-- No, it did not. This action violates foreign key constraint. 
-- Basically this film_id is used in other tables and cannot be deleted while still being used elsewhere

-- 9. Delete Mathmagical from the category table.
SELECT *
FROM category
WHERE name = 'Mathmagical';

DELETE FROM category
WHERE name = 'Mathmagical';

-- (Did it succeed? Why?)
-- It sure didn't work, same reason as above. It violates the foreign key constraints. The category_id is being used by film_category.

-- 10. Delete all links to Mathmagical in the film_category tale.
SELECT *
FROM film_category
WHERE category_id = 17;

DELETE FROM film_category
WHERE category_id = 17;

-- (Did it succeed? Why?)
-- It did work. It deleted the 5 rows updated earlier to category_id 17.
-- this worked because there are no constraints that rely on category_id not being NULL.

-- 11. Retry deleting Mathmagical from the category table, followed by retrying
-- to delete "Euclidean PI".
SELECT *
FROM film_category
WHERE category_id = 17;

DELETE FROM film_category
WHERE category_id = 17;

SELECT *
FROM film
WHERE film_id = 1001;

DELETE FROM film
WHERE film_id = 1001;

-- (Did either deletes succeed? Why?)
-- The delete from film_category was successful, but removed 0 results (since they had already been removed prior)
-- The delete from film failed again, still because of the foreign constraints.

-- 12. Check database metadata to determine all constraints of the film id, and
-- describe any remaining adjustments needed before the film "Euclidean PI" can
-- be removed from the film table.

-- there is also a constraint on the film_actor table, this film would need to be removed from film_actor table prior to deleting from film. 

SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
SELECT * FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE;
SELECT * FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS;
