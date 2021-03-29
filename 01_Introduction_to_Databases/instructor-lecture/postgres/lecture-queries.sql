-- SELECT ... FROM
-- Selecting the names for all countries

SELECT * -- * means all columns
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


/*
SELECT -- what columns to include
FROM -- what table is the source
WHERE -- filter (like if statements in java) -- what rows to include
*/

-- SELECT ... FROM ... WHERE
-- Selecting the cities in Ohio

SELECT *
FROM city
WHERE district = 'Ohio';

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

SELECT * -- columns 
FROM country -- table
WHERE indepyear = 1776;

-- Selecting countries not in Asia

SELECT *
FROM country
WHERE continent != 'Asia';

SELECT *
FROM country
WHERE NOT (continent = 'Asia');


-- Selecting countries that do not have an independence year

SELECT name
      , indepyear
FROM country
WHERE indepyear = NULL; -- null can NEVER = null



SELECT name
      , indepyear
FROM country
WHERE indepyear IS NULL; -- IS NULL determines that there IS NO value


-- Selecting countries that do have an independence year

SELECT name
      , indepyear
FROM country
WHERE indepyear IS NULL; -- IS NOT NULL determines that there IS a value

-- Selecting countries that have a population greater than 5 million

SELECT name
     , population
FROM country
WHERE population > 5000000;


-- SELECT ... FROM ... WHERE ... AND/OR
-- Selecting cities in Ohio and Population greater than 400,000

SELECT *
FROM city
WHERE (district = 'Ohio' OR district = 'Utah') -- use parenthesis to  group ORs together
    AND population < 175000;

-- Selecting country names on the continent North America or South America

SELECT name
     , continent
FROM country
WHERE continent = 'North America'
      OR continent = 'South America';
      
-- strings can be compared using LIKE
SELECT name
     , continent
FROM country
WHERE continent LIKE 'North%'; -- starts with

-- ILIKE is a case insensitive version of LIKE
-- only works on string comparison
SELECT name
     , continent
FROM country
WHERE continent ILIKE '%america'; -- ends with




-- SELECTING DATA w/arithmetic
-- Selecting the population, life expectancy, and population per area
--	note the use of the 'as' keyword

SELECT name
        , population
        , lifeexpectancy AS life_expectancy
        , population / surfacearea AS population_density -- do math inline 
FROM country;



