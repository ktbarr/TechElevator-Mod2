-- The following queries utilize the "dvdstore" database.

-- 1. All of the films that Nick Stallone has appeared in
-- (30 rows)

SELECT f.*
FROM film AS f;

SELECT fa.*
FROM film_actor AS fa;

SELECT a.*
FROM actor AS a;

SELECT f.title
FROM actor AS a
INNER JOIN film_actor AS fa
        ON fa.actor_id = a.actor_id
INNER JOIN film AS f
        ON f.film_id = fa.film_id
WHERE a.first_name = 'NICK' AND a.last_name = 'STALLONE';

-- 2. All of the films that Rita Reynolds has appeared in
-- (20 rows)

SELECT f.title
FROM actor AS a
INNER JOIN film_actor AS fa
        ON fa.actor_id = a.actor_id
INNER JOIN film AS f
        ON f.film_id = fa.film_id
WHERE a.first_name = 'RITA' AND a.last_name = 'REYNOLDS';

-- 3. All of the films that Judy Dean or River Dean have appeared in
-- (46 rows)

SELECT f.title
        , a.first_name AS actor_first_name
        , a.last_name AS actor_last_name
FROM actor AS a
INNER JOIN film_actor AS fa
        ON fa.actor_id = a.actor_id
INNER JOIN film AS f
        ON f.film_id = fa.film_id
WHERE a.last_name = 'DEAN' AND a.first_name = 'JUDY' OR a.first_name = 'RIVER';

-- 4. All of the the ‘Documentary’ films
-- (68 rows)

SELECT c.category_id
        , c.name
FROM category AS c;

SELECT f.title
FROM category AS c
INNER JOIN film_category AS fc
        ON fc.category_id = c.category_id
INNER JOIN film AS f
        ON f.film_id = fc.film_id
WHERE c.name = 'Documentary';

-- 5. All of the ‘Comedy’ films
-- (58 rows)

SELECT f.title
FROM category AS c
INNER JOIN film_category AS fc
        ON fc.category_id = c.category_id
INNER JOIN film AS f
        ON f.film_id = fc.film_id
WHERE c.name = 'Comedy';

-- 6. All of the ‘Children’ films that are rated ‘G’
-- (10 rows)

SELECT f.title
        , f.rating
FROM category AS c
INNER JOIN film_category AS fc
        ON fc.category_id = c.category_id
INNER JOIN film AS f
        ON f.film_id = fc.film_id
WHERE c.name = 'Children' AND f.rating = 'G';

-- 7. All of the ‘Family’ films that are rated ‘G’ and are less than 2 hours in length
-- (3 rows)

SELECT f.title
        , f.rating
        , f.length
FROM category AS c
INNER JOIN film_category AS fc
        ON fc.category_id = c.category_id
INNER JOIN film AS f
        ON f.film_id = fc.film_id
WHERE c.name = 'Family' AND f.rating = 'G' AND f.length < 120;

-- 8. All of the films featuring actor Matthew Leigh that are rated ‘G’
-- (9 rows)

SELECT f.title
        , f.rating
        , a.first_name
        , a.last_name
FROM actor AS a
INNER JOIN film_actor AS fa
        ON fa.actor_id = a.actor_id
INNER JOIN film AS f
        ON f.film_id = fa.film_id
WHERE a.first_name = 'MATTHEW' AND a.last_name = 'LEIGH' AND f.rating = 'G';

-- 9. All of the ‘Sci-Fi’ films released in 2006
-- (61 rows)

SELECT f.title
        , f.release_year
        , c.name
FROM category AS c
INNER JOIN film_category AS fc
        ON fc.category_id = c.category_id
INNER JOIN film AS f
        ON f.film_id = fc.film_id
WHERE c.name = 'Sci-Fi' AND f.release_year = 2006;

-- 10. All of the ‘Action’ films starring Nick Stallone
-- (2 rows)

SELECT f.title
        , c.name
        , a.first_name
        , a.last_name
FROM category AS c
INNER JOIN film_category AS fc
        ON fc.category_id = c.category_id
INNER JOIN film AS f
        ON f.film_id = fc.film_id
INNER JOIN film_actor AS fa
        ON fa.film_id = f.film_id
INNER JOIN actor AS a
        ON a.actor_id = fa.actor_id
WHERE c.name = 'Action' AND a.first_name = 'NICK' AND a.last_name = 'STALLONE';

-- 11. The address of all stores, including street address, city, district, and country
-- (2 rows)

SELECT s.*
FROM store AS s;

SELECT s.store_id
        , add.address
        , add.address2
        , city.city
        , add.district
        , ctry.country
FROM address AS add
INNER JOIN city
        ON city.city_id = add.city_id
INNER JOIN country AS ctry
        ON ctry.country_id = city.country_id
INNER JOIN store AS s
        ON s.address_id = add.address_id;

-- 12. A list of all stores by ID, the store’s street address, and the name of the store’s manager
-- (2 rows)

SELECT s.store_id
        , add.address
        , stf.first_name
        , stf.last_name
FROM store AS s
INNER JOIN staff AS stf
        ON stf.staff_id = s.manager_staff_id
INNER JOIN address AS add
        ON add.address_id = s.address_id
ORDER BY s.store_id;

-- 13. The first and last name of the top ten customers ranked by number of rentals 
-- (#1 should be “ELEANOR HUNT�? with 46 rentals, #10 should have 39 rentals)

-- using payment
SELECT cust.first_name
        , cust.last_name
        , COUNT(cust.customer_id) AS num_of_rentals
FROM customer AS cust
INNER JOIN payment AS p
        ON p.customer_id = cust.customer_id
GROUP BY cust.first_name
        , cust.last_name
ORDER BY num_of_rentals DESC
LIMIT 10;

-- using rental
SELECT cust.first_name
        , cust.last_name
        , COUNT(cust.customer_id) AS num_of_rentals
FROM customer AS cust
INNER JOIN rental AS r
        ON r.customer_id = cust.customer_id
GROUP BY cust.first_name
        , cust.last_name
ORDER BY num_of_rentals DESC
LIMIT 10;

-- 14. The first and last name of the top ten customers ranked by dollars spent 
-- (#1 should be “KARL SEAL�? with 221.55 spent, #10 should be “ANA BRADLEY�? with 174.66 spent)

SELECT cust.first_name
        , cust.last_name
        , SUM(p.amount) AS total_payment
FROM customer AS cust
INNER JOIN payment AS p
        ON p.customer_id = cust.customer_id
GROUP BY cust.first_name
        , cust.last_name
ORDER BY total_payment DESC
LIMIT 10;


-- 15. The store ID, street address, total number of rentals, total amount of sales (i.e. payments), and average sale of each store.
-- (NOTE: Keep in mind that an employee may work at multiple stores.)
-- (Store 1 has 7928 total rentals and Store 2 has 8121 total rentals)

SELECT s.store_id
        , a.address
--        , COUNT(DISTINCT i.inventory_id) AS inventory_count
        , COUNT(r.rental_id) AS rental_count
        , SUM(amount) AS total_sales
        , AVG(amount) AS average_sales
FROM store AS s
INNER JOIN address AS a
        ON s.address_id = a.address_id
INNER JOIN inventory AS i
        ON s.store_id = i.store_id
INNER JOIN rental AS r
        ON i.inventory_id = r.inventory_id
INNER JOIN payment AS p
        ON r.rental_id = p.rental_id
GROUP BY s.store_id
        , a.address_id
;

SELECT cust.*
FROM customer AS cust
ORDER BY cust.email;

SELECT s.store_id -- store
        , add.address -- address
        , COUNT(p.rental_id) AS total_num_of_rentals -- payment
        , SUM(p.amount) AS total_amount_sales -- payment
        , AVG(p.amount) AS average_sale_by_store -- payment
FROM store AS s
INNER JOIN address AS add
        ON add.address_id = s.address_id
INNER JOIN customer AS cust
        ON cust.store_id = s.store_id
INNER JOIN payment AS p
        ON p.customer_id = cust.customer_id
INNER JOIN rental AS r
        ON r.rental_id = p.rental_id
INNER JOIN inventory AS i
        ON i.store_id = s.store_id
INNER JOIN rental AS r
        ON r.inventory_id = i.inventory_id
GROUP BY s.store_id
        , add.address;

-- 16. The top ten film titles by number of rentals
-- (#1 should be “BUCKET BROTHERHOOD�? with 34 rentals and #10 should have 31 rentals)

SELECT f.title
        , COUNT(r.*) AS top_rentals
FROM film AS f
INNER JOIN inventory AS i
        ON i.film_id = f.film_id
INNER JOIN rental AS r
        ON r.inventory_id = i.inventory_id
GROUP BY f.title
ORDER BY top_rentals DESC
LIMIT 10;

-- 17. The top five film categories by number of rentals 
-- (#1 should be “Sports�? with 1179 rentals and #5 should be “Family�? with 1096 rentals)

SELECT c.name
        , COUNT(r.*) AS top_rental_categories
FROM film AS f
INNER JOIN inventory AS i
        ON i.film_id = f.film_id
INNER JOIN rental AS r
        ON r.inventory_id = i.inventory_id
INNER JOIN film_category AS fc
        ON fc.film_id = f.film_id
INNER JOIN category AS c
        ON c.category_id = fc.category_ID
GROUP BY c.name
ORDER BY top_rental_categories DESC
LIMIT 5;

-- 18. The top five Action film titles by number of rentals 
-- (#1 should have 30 rentals and #5 should have 28 rentals)

SELECT f.title
        , COUNT(r.*) top_action_films_by_rentals
FROM film AS f
INNER JOIN inventory AS i
        ON i.film_id = f.film_id
INNER JOIN rental AS r
        ON r.inventory_id = i.inventory_id
INNER JOIN film_category AS fc
        ON fc.film_id = f.film_id
INNER JOIN category AS c
        ON c.category_id = fc.category_ID
WHERE c.name = 'Action'
GROUP BY f.title
ORDER BY top_action_films_by_rentals DESC
LIMIT 5;

-- 19. The top 10 actors ranked by number of rentals of films starring that actor 
-- (#1 should be “GINA DEGENERES�? with 753 rentals and #10 should be “SEAN GUINESS�? with 599 rentals)

SELECT a.actor_id
        , a.first_name
        , a.last_name
        , COUNT(r.*) AS top_actors_by_rentals
FROM film as f
INNER JOIN inventory AS i
        ON i.film_id = f.film_id
INNER JOIN rental AS r
        ON r.inventory_id = i.inventory_id
INNER JOIN film_actor AS fa
        ON fa.film_id = f.film_id
INNER JOIN actor AS a
        ON a.actor_id = fa.actor_id
GROUP BY a.actor_id
        , a.first_name
        , a.last_name
ORDER BY top_actors_by_rentals DESC
LIMIT 10;

-- 20. The top 5 “Comedy�? actors ranked by number of rentals of films in the “Comedy�? category starring that actor 
-- (#1 should have 87 rentals and #5 should have 72 rentals)

SELECT a.actor_id
        , a.first_name
        , a.last_name
        , COUNT(r.*) AS top_actors_by_rentals
FROM film as f
INNER JOIN inventory AS i
        ON i.film_id = f.film_id
INNER JOIN rental AS r
        ON r.inventory_id = i.inventory_id
INNER JOIN film_actor AS fa
        ON fa.film_id = f.film_id
INNER JOIN actor AS a
        ON a.actor_id = fa.actor_id
INNER JOIN film_category AS fc
        ON fc.film_id = f.film_id
INNER JOIN category AS c
        ON c.category_id = fc.category_id
WHERE c.name = 'Comedy'
GROUP BY a.actor_id
        , a.first_name
        , a.last_name
ORDER BY top_actors_by_rentals DESC
LIMIT 5;
