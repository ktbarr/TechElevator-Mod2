-- ORDERING RESULTS
-- structure

--SELECT first_name     -- 6) LAST determine which columns to use
--        , last_name
--        , state
--FROM student          -- 1) - db determines source
--WHERE class = 'Java'  -- 2) - filters the rows
--GROUP BY state        -- 3) - perform any grouping
-- HAVING clause        -- 4) - filter the groups
--ORDER BY last_name    -- 5) - sort
--        , first_name
-- LIMIT 5;             -- this will limit to top 5 results, but should sort first


-- Populations of all countries in descending order
SELECT name
        , population
FROM country
ORDER BY population DESC -- defaults to ascending order; use DESC for descending order
LIMIT 5; -- limits to top 5, without ORDER BY this would just grab based on file

--Names of countries and continents in ascending order
SELECT name
        , continent
FROM country
ORDER BY continent
        , name;

-- LIMITING RESULTS
-- The name and average life expectancy of the countries with the 10 highest life expectancies.
SELECT name
        , lifeexpectancy
FROM country
WHERE lifeexpectancy IS NOT NULL
ORDER BY lifeexpectancy DESC
LIMIT 10;

-- CONCATENATING OUTPUTS

-- The name & state of all cities in California, Oregon, or Washington.
-- "city, state", sorted by state then city

-- concat function will concatnate 2 strings --> preferance is yours on which to use.
SELECT concat(name, ', ', district) AS city_state
FROM city
WHERE district IN('California'
                  ,'Oregon'
                  ,'Washington')
ORDER BY district
        , name;

-- || concatenates 2 strings in Postgres        
SELECT name || ', ' || district AS city_state
FROM city
WHERE district IN('California'
                  ,'Oregon'
                  ,'Washington')
ORDER BY district
        , name;        

-- AGGREGATE FUNCTIONS
/*
sum
average
min
max
count
*/

-- Average Life Expectancy in the World
SELECT COUNT(name) AS country_count
        , COUNT(lifeexpectancy) AS life_expectancy_count
        , AVG(lifeexpectancy) AS average_life
        , MIN(lifeexpectancy) AS minimum_life
        , MAX(lifeexpectancy) AS maximum_life
FROM country;

-- Total population in Ohio
SELECT SUM(population) AS ohio_total_population
FROM city
WHERE district = 'Ohio';

-- total population
SELECT SUM(population) AS ohio_total_population
FROM city
WHERE district = 'Ohio';

-- The surface area of the smallest country in the world
SELECT MIN(surfacearea)
FROM country;

-- The 10 largest countries in the world by surface area
SELECT name
        , surfacearea
FROM country
ORDER BY surfacearea DESC
LIMIT 10;

-- The number of countries who declared independence in 1991
SELECT COUNT(indepyear)
FROM country
WHERE indepyear = 1991;

-- GROUP BY
-- Total population of the USA
SELECT SUM(population) AS USA_total_population
FROM city
WHERE countrycode = 'USA';

-- Total population of each state in the USA -- must GROUP BY to get by each state
        -- if there is a column in SELECT it should also be in GROUP BY
SELECT district AS state
        , SUM (population) AS total_population
FROM city
WHERE countrycode = 'USA'
GROUP BY district
ORDER BY district;

-- Count the number of countries where each language is spoken, ordered from most countries to least

-- Average life expectancy of each continent ordered from highest to lowest

-- Exclude Antarctica from consideration for average life expectancy

-- Sum of the population of cities in each state in the USA ordered by state name

-- The average population of cities in each state in the USA ordered by state name

-- SUBQUERIES
-- Find the names of cities under a given government leader
SELECT *
FROM city
WHERE countrycode IN (
        'AIA'
        , 'ATG'
        , 'AUS'
        );


SELECT code
FROM country
WHERE headofstate = 'Elisabeth II';

-- subquery
SELECT *
FROM city
WHERE countrycode IN (
        SELECT code
        FROM country
        WHERE headofstate = 'Elisabeth II'
        );
        
SELECT c.name as city
        ,(
        SELECT name
        FROM country
        where code = c.countrycode
        ) AS country
FROM city AS c
WHERE countrycode IN (
        SELECT code
        FROM country
        WHERE headofstate = 'Elisabeth II'
        );

-- Find the names of cities whose country they belong to has not declared independence yet

-- Additional samples
-- You may alias column and table names to be more descriptive

-- Alias can also be used to create shorthand references, such as "c" for city and "co" for country.

-- Ordering allows columns to be displayed in ascending order, or descending order (Look at Arlington)

-- Limiting results allows rows to be returned in 'limited' clusters,where LIMIT says how many, and OFFSET (optional) specifies the number of rows to skip

-- Most database platforms provide string functions that can be useful for working with string data. In addition to string functions, string concatenation is also usually supported, which allows us to build complete sentences if necessary.

-- Aggregate functions provide the ability to COUNT, SUM, and AVG, as well as determine MIN and MAX. Only returns a single row of value(s) unless used with GROUP BY.

-- Counts the number of rows in the city table

-- Also counts the number of rows in the city table

-- Gets the SUM of the population field in the city table, as well as
-- the AVG population, and a COUNT of the total number of rows.

-- Gets the MIN population and the MAX population from the city table.

-- Using a GROUP BY with aggregate functions allows us to summarize information for a specific column. For instance, we are able to determine the MIN and MAX population for each countrycode in the city table.
