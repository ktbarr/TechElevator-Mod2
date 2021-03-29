package com.techelevator.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.models.dto.City;
import com.techelevator.models.interfaces.CityDAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCityDAO implements CityDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCityDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(City newCity) 
	{
		
		String sqlInsertCity = "INSERT INTO city(id, name, countrycode, district, population) " +
							   "VALUES(?, ?, ?, ?, ?)";
		
		// call the nextval() function in postgres
		newCity.setId(getNextCityId());
		
		// then insert the data
		jdbcTemplate.update(sqlInsertCity, newCity.getId(),
										  newCity.getName(),
										  newCity.getCountryCode(),
										  newCity.getDistrict(),
										  newCity.getPopulation());
	}
	
	@Override
	public City findCityById(long id) {
		City theCity = null;
		String sqlFindCityById = "SELECT id, name, countrycode, district, population "+
							   "FROM city "+
							   "WHERE id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCityById, id);
		if(results.next()) {
			theCity = mapRowToCity(results);
		}
		return theCity;
	}

	@Override
	public List<City> findCityByCountryCode(String countryCode) 
	{
		// 1. create a container to hold the cities
		List<City> cities = new ArrayList<City>();
		
		// write your query
		String query = "SELECT id\r\n" + 
				"        , name\r\n" + 
				"        , countrycode\r\n" + 
				"        , district\r\n" + 
				"        , population\r\n" + 
				"FROM city\r\n" + 
				"WHERE countrycode = ?;";

		// execute the query
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, countryCode);
		
		// loop through the results
		// convert each row to a city object
		// add the city to the list
		while(rows.next())
		{
			City city = mapRowToCity(rows);
			
			cities.add(city);
		}
		
		// return the results
		return cities;
	}

	@Override
	public List<City> findCityByDistrict(String district) {

		// 1. create a container to hold the cities
		List<City> cities = new ArrayList<City>();
		
		// write your query
		String query = "SELECT id\r\n" + 
				"        , name\r\n" + 
				"        , countrycode\r\n" + 
				"        , district\r\n" + 
				"        , population\r\n" + 
				"FROM city\r\n" + 
				"WHERE district = ?;";

		// execute the query
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, district);
		
		// loop through the results
		// convert each row to a city object
		// add the city to the list
		while(rows.next())
		{
			// this code is duplicated so we created a mapRowToCity function
//			long id = rows.getLong("id");
//			String name = rows.getString("name");
//			String countrycode = rows.getString("countrycode");
//			String state = rows.getString("district");
//			int population = rows.getInt("population");
//			
//			// create a city object
//			City city = new City();
//			city.setId(id);
//			city.setName(name);
//			city.setCountryCode(countrycode);
//			city.setDistrict(state);
//			city.setPopulation(population);
			
			City city = mapRowToCity(rows);
			
			cities.add(city);
		}
		
		// return the results
		return cities;
	}

	@Override
	public void update(City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	private long getNextCityId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_city_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new city");
		}
	}

	private City mapRowToCity(SqlRowSet results) {
		City theCity;
		theCity = new City();
		theCity.setId(results.getLong("id"));
		theCity.setName(results.getString("name"));
		theCity.setCountryCode(results.getString("countrycode"));
		theCity.setDistrict(results.getString("district"));
		theCity.setPopulation(results.getInt("population"));
		return theCity;
	}
}
