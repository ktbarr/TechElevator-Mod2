-- SELECT ... FROM
-- Selecting the names for all countries

SELECT *  -- means all columns, will run full database
FROM country;

SELECT name
FROM country;

-- Selecting the name and population of all countries
SELECT name 
        , population
FROM country;

-- Selecting all columns from the city table
SELECT *
FROM city;

-- SELECT ... FROM ... WHERE
-- Selecting the cities in Ohio
SELECT *
FROM city
WHERE district = 'Ohio'; -- strings are single quotes, single equal. 

/*
= 
>
<
>=
<=
BETWEEN
!=
*/

-- Selecting countries that gained independence in the year 1776
SELECT *
FROM country
WHERE indepyear = 1776;

-- Selecting countries not in Asia
SELECT *
FROM country
WHERE continent != 'Asia';

-- these are the same

SELECT *
FROM country
WHERE NOT (continent = 'Asia');


-- Selecting countries that do not have an independence year
        -- null can NEVER = null
SELECT name
        , indepyear
FROM country
WHERE indepyear IS null; -- checks if there is NO value

-- Selecting countries that do have an independence year
SELECT name
        , indepyear
FROM country
WHERE indepyear IS NOT null;

-- Selecting countries that have a population greater than 5 million
SELECT name
        , population
FROM country
WHERE population > 5000000;


-- SELECT ... FROM ... WHERE ... AND/OR
-- Selecting cities in Ohio and Population greater than 400,000
SELECT name
        , district
        , population
FROM city
WHERE district = 'Ohio'
   AND population > 400000;

-- Selecting cities in Ohio and New York whose Populations are greater than 400,000
SELECT name
        , district
        , population
FROM city
WHERE (district = 'Ohio' OR district = 'New York') -- use () to group ORs together
   AND population > 400000;

-- Selecting country names on the continent North America or South America
SELECT name
       , continent
FROM country
WHERE continent = 'North America' 
        OR continent = 'South America';

-- Strings can be compared using the LIKE keyword
SELECT name
       , continent
FROM country
WHERE continent LIKE 'North%'; -- Starts With 

-- Strings can be compared using the LIKE keyword
SELECT name
       , continent
FROM country
WHERE continent LIKE '%America'; -- ends With 

-- Strings can be compared using the LIKE keyword
SELECT name
       , continent
FROM country
WHERE continent ILIKE '%america'; -- ends With ; ILIKE makes it case insensitive
        

-- SELECTING DATA w/arithmetic
-- Selecting the population, life expectancy, and population per area
--	note the use of the 'as' keyword
SELECT name
        , population
        , lifeexpectancy
        , population / surfacearea AS population_density -- do math inline 
        -- this will give you pop by surface area, AS allows you to name the new column.
FROM country;


