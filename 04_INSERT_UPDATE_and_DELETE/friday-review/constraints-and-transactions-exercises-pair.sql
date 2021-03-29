-- Write queries to return the following:
-- Make the following changes in the "world" database.

-- 1. Add Superman's hometown, Smallville, Kansas to the city table. The 
-- countrycode is 'USA', and population of 45001. (Yes, I looked it up on 
-- Wikipedia.)

-- 2. Add Kryptonese to the countrylanguage table. Kryptonese is spoken by 0.0001
-- percentage of the 'USA' population.

-- 3. After heated debate, "Kryptonese" was renamed to "Krypto-babble", change 
-- the appropriate record accordingly.

-- 4. Set the US captial to Smallville, Kansas in the country table.

-- 5. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)

-- 6. Return the US captial to Washington.

-- 7. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)

-- 8. Reverse the "is the official language" setting for all languages where the
-- country's year of independence is within the range of 1800 and 1972 
-- (exclusive). 
-- (590 rows affected)

-- working with sub queries
SELECT countrycode
        , language
        , isofficial
        , NOT isofficial
FROM countrylanguage
WHERE countrycode IN 
(
        SELECT code
        FROM country
        where indepyear BETWEEN 1800 AND 1972
);


UPDATE countrylanguage
SET isofficial = NOT isofficial
WHERE countrycode IN 
(
        SELECT code
        FROM country
        where indepyear BETWEEN 1800 AND 1972
);


-- working with joins
SELECT cl.countrycode
        , cl.language
        , cl.isofficial
        , NOT cl.isofficial
FROM countrylanguage AS cl
INNER JOIN country AS c
        ON cl.countrycode = c.code
WHERE c.indepyear BETWEEN 1800 AND 1972;


BEGIN TRANSACTION;

        -- to update by pulling data from another table
        UPDATE countrylanguage AS cl -- alias the update table
        SET isofficial = NOT isofficial
        FROM country AS c -- don't INNER JOIN the country table just use it as the FROM source
        WHERE cl.countrycode = c.code
                AND c.indepyear BETWEEN 1800 AND 1972;
                
        SELECT *
        FROM countrylanguage
        WHERE countrycode IN ('USA', 'AFG');

COMMIT TRANSACTION;



-- 9. Convert population so it is expressed in 1,000s for all cities. (Round to
-- the nearest integer value greater than 0.)
-- (4079 rows affected)

-- 10. Assuming a country's surfacearea is expressed in square miles, convert it to 
-- square meters for all countries where French is spoken by more than 20% of the 
-- population.
-- (7 rows affected)

-- 1609.34 sqft pe rsqmile

-- update
-- table: country
-- field: surfacearea

-- filter (which countries?)
-- table: countrylanguage
-- field: language - French
-- field: percentage - > 20

SELECT *
FROM countrylanguage
WHERE language ILIKE 'French'
        AND percentage >= 20;
        
UPDATE country
SET surfacearea = (surfacearea * 1609.34)
WHERE code IN
(
        SELECT countrycode
        FROM countrylanguage
        WHERE language ILIKE 'French'
                AND percentage >= 20
);
