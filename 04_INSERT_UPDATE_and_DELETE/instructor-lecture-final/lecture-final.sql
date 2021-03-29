/*
--sytax for select
SELECT *
FROM countrylanguage;

--syntax for insert
INSERT INTO <table>(<available columns>)
VALUES (<values>)
*/

-- INSERT

-- 1. Add Klingon as a spoken language in the USA
-- 2. Add Klingon as a spoken language in Great Britain
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



INSERT INTO countrylanguage
(
        countrycode
        , language
        , isofficial
        , percentage
)
VALUES
(
        'GBR'
        , 'Klingon'
        , false
        , 0.2
);


INSERT INTO countrylanguage (countrycode, language, isofficial, percentage) VALUES ('GBR', 'Klingon', false, 0.2);

INSERT INTO countrylanguage (countrycode, language, isofficial, percentage)
VALUES ( 'GBR', 'Esperanto', false, 0.2 )
        , ( 'USA', 'Esperanto', false, 0.002 )
;

-- UPDATE

-- Syntax
/*

UPDATE <table name>
SET <column> = value
        , <column> = value
WHERE <condition> -- ALWAYS HAVE A WHERE CLAUSE

*/

-- 1. Update the capital of the USA to Houston
-- 2. Update the capital of the USA to Washington DC and the head of state

-- 3813 - Washington
-- 3796 - Houston
SELECT *
FROM country
WHERE code = 'USA'

select *
from city
where name = 'Houston'

-- change capital to Houston
UPDATE country
SET capital = 3796
WHERE code = 'USA';

-- change capital to Washington and Head of State

UPDATE country
SET capital = 3813
        , headofstate = 'Joe Biden'
WHERE code = 'USA';

SELECT *
FROM country
WHERE code = 'USA'

-- DELETE

-- 1. Delete English as a spoken language in the USA
-- 2. Delete all occurrences of the Klingon language 

DELETE FROM countrylanguage
WHERE countrycode = 'USA'
        AND language = 'English';
        
        
DELETE FROM countrylanguage
WHERE language = 'Klingon';


-- REFERENTIAL INTEGRITY

-- 1. Try just adding Elvish to the country language table.

-- illegal because countrycode, isofficial and percentage are all required (NOT NULL constraint)
INSERT INTO countrylanguage
(
        language
)
VALUES
(
        'Elvish'
);

-- 2. Try inserting English as a spoken language in the country ZZZ. What happens?
-- illegal because there is no ZZZ country in the country table
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
SELECT *
FROM country
WHERE code = 'USA';

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

-- 3. Let's relocate the USA to the continent - 'Outer Space'
UPDATE country
SET continent = 'Outer Space'
WHERE code = 'USA';


-- How to view all of the constraints

SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS;
SELECT * FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE;
SELECT * FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS;


-- TRANSACTIONS

-- change capital to Houston (withough WHERE)
Begin Transaction;

        UPDATE country
        SET capital = 3796;
        
        -- this shows all updated capitals
        SELECT code
                , capital
        FROM country;

-- rolls back any changes
Rollback Transaction;

-- verify that no capitals were actually updated
SELECT code
        , capital
FROM country;

-- 1. Try deleting all of the rows from the country language table and roll it back.
BEGIN TRANSACTION;

        DELETE FROM countrylanguage;
        
        SELECT * 
        FROM countrylanguage;
        
ROLLBACK TRANSACTION;


-- 2. Try updating all of the cities to be in the USA and roll it back

-- 3. Demonstrate two different SQL connections trying to access the same table where one happens to be inside of a transaction but hasn't committed yet.
BEGIN TRANSACTION;

        UPDATE country
        SET lifeexpectancy = 82.2
        WHERE code = 'USA';
        
        SELECT * FROM country
        where code = 'USA';
        
COMMIT TRANSACTION;

SELECT * FROM country
where code = 'USA';

