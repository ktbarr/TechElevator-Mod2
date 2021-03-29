package com.techelevator.city;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UnitTestReview
{
	
	@Test
	public void save_should_insert_city_into_database() throws SQLException
	{
		//arrange
		
		//create data source
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/world");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
		
		// use for verfication
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM city WHERE id = ?;";
		
		// create the dao -- this is the object under test
		CityDAO dao = new JDBCCityDAO(dataSource);
		
		City city = new City();
		city.setName("Grantsville");
		city.setDistrict("Utah");
		city.setCountryCode("USA");
		city.setPopulation(10001);
		
		//act
		dao.save(city);
		
		//assert
		// where I test if it worked
		
		// check the database
		SqlRowSet rows = template.queryForRowSet(sql, city.getId());
		
		if(rows.next())
		{
			assertEquals(city.getName(), rows.getString("name"));
		}
		else
		{
			fail("The city was not inserted correctly");
		}
		
		dataSource.getConnection().rollback();
	}

	@Test
	public void getCityAndState_should_return_city_and_state_names_with_comma()
	{
		// arrange
		String expected = "Grantsville, Utah";
		City city = new City();
		city.setName("Grantsville");
		city.setDistrict("Utah");
		
		// act
		String cityState = city.getCityAndState();
		
		// assert
		assertEquals("because the city and state should be concatenated with a comma",expected, cityState);
	}
	
}
