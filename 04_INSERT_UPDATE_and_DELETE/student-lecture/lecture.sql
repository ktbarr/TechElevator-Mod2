
SELECT *
FROM countrylanguage;

-- INSERT
INSERT INTO <table>(<available columns>)
VALUES (<values>)

-- 1. Add Klingon as a spoken language in the USA
INSERT INTO countrylanguage
(
        countrycode
        , language
        , isofficial
        , percentage
)
VALUES
(
        'USA'
        , 'Klingon'
        , false
        , 0.001
);

SELECT *
FROM countrylanguage
WHERE countrycode = 'USA';

-- 2. Add Klingon as a spoken language in Great Britain
-- start with the above format, after that you can reformat to the below (or on 1 line)
-- if someone else needs to read this or debug, write as above

INSERT INTO countrylanguage(countrycode, language, isofficial, percentage)
VALUES('GBR', 'Klingon', false, 0.2);

SELECT *
FROM countrylanguage
WHERE countrycode = 'GBR';

-- UPDATE
-- Syntax
/*

UPDATE <table name>
SET <column> = value
       , <column> = value
WHERE <condition>  -- ALWAYS have a where clause, or it will update EVERY row

*/

SELECT *
FROM country
WHERE code = 'USA';

SELECT *
FROM city
WHERE name = 'Houston';

-- current: 3813
-- update: 3796

-- 1. Update the capital of the USA to Houston
UPDATE country
SET capital = 3796
WHERE code = 'USA';

-- 2. Update the capital of the USA back to Washington DC and the head of state
UPDATE country
SET capital = 3813
        , headofstate = 'Joseph R. Biden'
WHERE code = 'USA';


-- DELETE
-- syntax


-- 1. Delete English as a spoken language in the USA

DELETE FROM countrylanguage
WHERE countrycode = 'USA' 
        AND language = 'English';
       
-- 2. Delete all occurrences of the Klingon language 
DELETE FROM countrylanguage
WHERE language = 'Klingon';

-- REFERENTIAL INTEGRITY

-- 1. Try just adding Elvish to the country language table.

-- this is illegal because countrycode is required (constraint)
INSERT INTO countrylanguage
(
        , language
)
VALUES
(
        , 'Elvish'
);

-- 2. Try inserting English as a spoken language in the country ZZZ. What happens?
-- illegal because ZZZ is not a known countrycode
INSERT INTO countrylanguage
(
        countrycode
        , language
        , isofficial
        , percentage
)
VALUES
(
        'ZZZ'
        , 'English'
        , false
        , 0.2
);


-- 3. Try deleting the country USA. What happens?
-- illegal because code has dependencies based on PK in countrylanguage
DELETE FROM country
WHERE code = 'USA';

-- CONSTRAINTS

-- 1. Try inserting English as a spoken language in the USA
INSERT INTO countrylanguage
(
        countrycode
        , language
        , isofficial
        , percentage
)
VALUES
(
        'USA'
        , 'English'
        , false
        , 0.2
);

-- 2. Try again. What happens?
-- can't insert twice

-- 3. Let's relocate the USA to the continent - 'Outer Space'
-- violates check constraint (can validate against valid fields)
UPDATE country
SET continent = 'Outer Space'
WHERE code = 'USA';

-- How to view all of the constraints

SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
SELECT * FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE;
SELECT * FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS;


-- TRANSACTIONS
-- ALL or NOTHING --- AKA: 
-- A-tomic 
-- C-onsistent , if I do a select statement I get consistent results 
-- I-solated , one time
-- D-urable , once it's done - it's done

-- 1. Try deleting all of the rows from the country language table and roll it back.


-- 2. Try updating all of the cities to be in the USA and roll it back

-- 3. Demonstrate two different SQL connections trying to access the same table where one happens to be inside of a transaction but hasn't committed yet.