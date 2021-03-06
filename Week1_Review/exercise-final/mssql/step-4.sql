-- select the park name, campground name, open_from_mm, open_to_mm & daily_fee ordered by park name and then campground name
select p.name as park, c.name as campground, c.open_from_mm, c.open_to_mm, daily_fee
from park p
join campground c on c.park_id = p.park_id
order by park, campground;

-- select the park name and the total number of campgrounds for each park ordered by park name
select p.name, count(c.campground_id)
from park p
join campground c on c.park_id = p.park_id
group by p.park_id, p.name
order by p.name;


-- select the park name, campground name, site number, max occupancy, accessible, max rv length, utilities where the campground name is 'Blackwoods'
select
	p.name as park,
	c.name as campground,
	s.site_number,s.max_occupancy,s.accessible,s.max_rv_length,s.utilities
from park p
join campground c on c.park_id = p.park_id
join site s on s.campground_id = c.campground_id
where c.name = 'Blackwoods';

/*
  select park name, campground, total number of sites (column alias 'number_of_sites') ordered by park
  data should look like below:
  -------------------------------------------------
    park				campground							number_of_sites
	Acadia				Blackwoods							12
    Acadia				Seawall								12
    Acadia				Schoodic Woods						12
    Arches				"Devil's Garden"					8
    Arches				Canyon Wren Group Site				1
    Arches				Juniper Group Site					1
    Cuyahoga Valley		The Unnamed Primitive Campsites		5
  -------------------------------------------------
*/

select
	p.name as park,
	c.name as campground,
	count(s.site_id) as number_of_sites
from park p
join campground c on c.park_id = p.park_id
join site s on s.campground_id = c.campground_id
group by s.campground_id,p.name,c.name
order by p.name;


-- select site number, reservation name, reservation from and to date ordered by reservation from date

select
	s.site_number,r.name, r.from_date,r.to_date
from reservation r
join site s on s.site_id = r.site_id
order by from_date;

/*
  select campground name, total number of reservations for each campground ordered by total reservations desc
  data should look like below:
  -------------------------------------------------
    name								total_reservations
	Seawall								13
    Blackwoods							9
    "Devil's Garden"					7
    Schoodic Woods						7
    Canyon Wren Group Site				4
    Juniper Group Site					4
  -------------------------------------------------
*/
select c.name, count(r.site_id) as total_reservations
from reservation r
join site s on s.site_id = r.site_id
join campground c on c.campground_id = s.campground_id
group by c.name
order by total_reservations desc;

