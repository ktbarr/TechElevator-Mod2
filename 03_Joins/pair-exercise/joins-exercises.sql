-- Write queries to return the following:
-- The following queries utilize the "world" database.

-- 1. The city name, country name, and city population of all cities in Europe with population greater than 1 million
-- (36 rows)

SELECT city.name AS city
        , country.name AS country
        , city.population AS population
FROM country
INNER JOIN city
    ON country.code = city.countrycode
WHERE city.population > 1000000 AND country.continent = 'Europe';


-- 2. The city name, country name, and city population of all cities in countries where French is an official language 
-- and the city population is greater than 1 million
-- (2 rows)

SELECT cl.*
FROM countrylanguage AS cl;

SELECT city.name AS city
        , country.name AS country
        , city.population AS population
FROM country
INNER JOIN city
        ON country.code = city.countrycode
INNER JOIN countrylanguage AS cl
        ON country.code = cl.countrycode
WHERE cl.language = 'French' AND cl.isofficial = 'TRUE' AND city.population > 1000000;

-- 3. The name of the countries and continents where the language Javanese is spoken
-- (1 row)

SELECT country.name AS country
        , country.continent AS continent
FROM country
INNER JOIN countrylanguage AS cl
        ON country.code = cl.countrycode
WHERE cl.language = 'Javanese';

-- 4. The names of all of the countries in Africa that speak French as an official language
-- (5 row)

SELECT country.name
        , cl.language
FROM country
INNER JOIN countrylanguage AS cl
        ON country.code = cl.countrycode
WHERE country.continent = 'Africa' AND cl.language = 'French' AND cl.isofficial = 'TRUE';

-- 5. The average city population of cities in Europe
-- (average city population in Europe: 287,684)

SELECT AVG(city.population) AS avg_population_cities_in_Europe
FROM city
INNER JOIN country
        ON city.countrycode = country.code
WHERE country.continent = 'Europe';

-- 6. The average city population of cities in Asia
-- (average city population in Asia: 395,019)

SELECT AVG(city.population) AS avg_population_cities_in_asia
FROM city
INNER JOIN country
        ON city.countrycode = country.code
WHERE country.continent = 'Asia';


-- 7. The number of cities in countries where English is an official language
-- (number of cities where English is official language: 523)

SELECT COUNT(city.name)
FROM city
INNER JOIN countrylanguage AS cl
        ON city.countrycode = cl.countrycode
WHERE cl.language = 'English' AND cl.isofficial = 'TRUE';

-- 8. The average population of cities in countries where the official language is English
-- (average population of cities where English is official language: 285,809)

SELECT AVG(city.population)
FROM city
INNER JOIN countrylanguage AS cl
        ON city.countrycode = cl.countrycode
WHERE cl.language = 'English' AND cl.isofficial = 'TRUE';

-- 9. The names of all of the continents and the population of the continent’s largest city
-- (6 rows, largest population for North America: 8,591,309)

SELECT

-- 10. The names of all of the cities in South America that have a population of more than 1 million people 
-- and the official language of each city’s country
-- (29 rows)

SELECT city.name,
    cl.language,
    country.continent
FROM city
    INNER JOIN country ON country.code = city.countrycode
    INNER JOIN countrylanguage AS cl ON cl.countrycode = country.code
WHERE country.continent = 'South America'
    AND city.population > 1000000
GROUP BY city.name,
    cl.language,
    country.continent
ORDER BY city.name;




        

