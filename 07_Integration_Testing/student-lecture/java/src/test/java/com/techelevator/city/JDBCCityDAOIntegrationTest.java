package com.techelevator.city;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

// this guarantees methods execute in the same order every time
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCCityDAOIntegrationTest
{
	// this is the NEW country code we use for all tests
	private static final String TEST_COUNTRY = "XYZ";

	//SingleConnectionDataSource allows for transactions -- important so that we can ROLLBACK the tests
	private static SingleConnectionDataSource dataSource;
	private static JdbcTemplate jdbcTemplate;
	
	// this is the object under test
	private JDBCCityDAO dao;

	
	@BeforeClass 
	public static void setupDataSource()
	{
		//setup the data source and transaction for ALL tests (one big transaction)
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/world");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		// disable the transaction auto commit - so that we can ROLLBACK
		dataSource.setAutoCommit(false);

		// we will use the jdbcTemplate to verify database results
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@AfterClass 
	public static void closeDataSource() throws SQLException
	{
		// after ALL tests run destroy the data source
		dataSource.destroy();
	}

	@Before
	public void setup()
	{
		// this is the arrange that runs before each test
		insertCountry();		

		// create a new instance so that we have a CLEAN/FRESH DAO for each test
		dao = new JDBCCityDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException
	{
		// ROLLBACK the changes in the database after EACH test
		dataSource.getConnection().rollback();
	}

	@Test
	public void save_new_city_and_read_it_back() throws SQLException
	{
		// arrange - testCity is the city object being verified - the one being manipulated by the dao
		City testCity = buildCity("SQL Station", "South Dakota", "USA", 65535);

		// act
		dao.save(testCity); 
		
		// there is a temptation to test if the save function worked
		// by just using the dao.findCityByID() function
		// RESIST the temptation!
		City actualCity = dao.findCityById(testCity.getId());

		//assert 
		assertNotNull(testCity.getId());
		assertCitiesAreEqual(testCity, actualCity);
	}

	@Test
	public void returns_cities_by_country_code()
	{
		// arrange
		City testCity = buildCity("SQL Station", "South Dakota", TEST_COUNTRY, 65535);
		testCity.setId(15001L);
		insertCity(testCity);

		// act
		List<City> results = dao.findCityByCountryCode(TEST_COUNTRY);

		// assert
		assertNotNull(results);
		assertEquals(1, results.size());
		City daoCity = results.get(0);
		assertCitiesAreEqual(testCity, daoCity);
	}

	@Test
	public void returns_multiple_cities_by_country_code()
	{
		// arrange - i.e. set up the database so that the dao has data to return
		City testCity1 = buildCity("SQL Station", "South Dakota", TEST_COUNTRY, 65535);
		City testCity2 = buildCity("Postgres Point", "North Dakota", TEST_COUNTRY, 65535);

		testCity1.setId(15001L);
		testCity2.setId(15002L);

		insertCity(testCity1);
		insertCity(testCity2);

		// act
		List<City> results = dao.findCityByCountryCode(TEST_COUNTRY);

		// assert
		assertNotNull(results);
		assertEquals(2, results.size());
		assertCitiesAreEqual(testCity1, results.get(0));
		assertCitiesAreEqual(testCity2, results.get(1));
	}

	@Test
	public void returns_cities_by_district()
	{		
		// arrange
		String district = "Tech Elevator";
		
		City testCity = buildCity("SQL Station", district, TEST_COUNTRY, 65535);
		testCity.setId(15001L);
		insertCity(testCity);

		// act
		List<City> results = dao.findCityByDistrict(district);

		// assert
		assertNotNull(results);
		assertEquals(1, results.size());
		
		City daoCity = results.get(0);
		assertCitiesAreEqual(testCity, daoCity);
	}

	// assertion helpers
	private void assertCitiesAreEqual(City expected, City actual)
	{
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDistrict(), actual.getDistrict());
		assertEquals(expected.getCountryCode(), actual.getCountryCode());
		assertEquals(expected.getPopulation(), actual.getPopulation());
	}

	// DTO helpers
	private City buildCity(String name, String district, String countryCode, int population)
	{
		City theCity = new City();
		theCity.setName(name);
		theCity.setDistrict(district);
		theCity.setCountryCode(countryCode);
		theCity.setPopulation(population);
		return theCity;
	}
	
	// Database helpers -- these are used to INDEPENDENTLY VERIFY the results in the database
	private void insertCountry()
	{
		// we need this new country in the database for each of the tests
		String sqlInsertCountry = "INSERT INTO country " +
								  " ( " + 
								  "		code " +
								  "		, name " +
								  "		, continent " +
								  "		, region " +
								  "		, surfacearea " +
								  "		, indepyear " +
								  "		, population " +
								  "		, lifeexpectancy " +
								  "		, gnp " +
								  "		, gnpold " +
								  "		, localname " +
								  "		, governmentform " +
								  "		, headofstate " +
								  "		, capital " +
								  "		, code2 " +
								  ")  " +
								  "VALUES  " +
								  "( " +
								  "		? " +
								  "		, 'Afghanistan' " +
								  "		, 'Asia' " +
								  "		, 'Southern and Central Asia' " +
								  "		, 652090 " +
								  "		, 1919 " +
								  "		, 22720000 " +
								  "		, 45.9000015 " +
								  "		, 5976.00 " +
								  "		, NULL " +
								  "		, 'Afganistan/Afqanestan' " +
								  "		, 'Islamic Emirate' " +
								  "		, 'Mohammad Omar' " +
								  "		, 1 " +
								  "		, 'AF' " +
								  ");";
		
		jdbcTemplate.update(sqlInsertCountry, TEST_COUNTRY);
	}
	
	private void insertCity(City city)
	{		
		String query = "INSERT INTO city " + 
				"( " + 
				"        id " + 
				"        , name " + 
				"        , countrycode " + 
				"        , district " + 
				"        , population " + 
				") " + 
				"VALUES (?, ?, ?, ?, ?);";
		
		jdbcTemplate.update(query
							, city.getId()
							, city.getName()
							, city.getCountryCode()
							, city.getDistrict()
							, city.getPopulation());
				
	}
	
	private City selectCityById(long id)
	{
		City city = null;
		
		String sql = "SELECT id\r\n" + 
						"        , name\r\n" + 
						"        , countrycode\r\n" + 
						"        , district\r\n" + 
						"        , population\r\n" + 
						"FROM city\r\n" + 
						"WHERE id = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);
		
		if(row.next())
		{
			String name = row.getString("name");
			String district = row.getString("district");
			String countryCode = row.getString("countrycode");
			int population = row.getInt("population");
			
			city = buildCity(name, district, countryCode, population);
			city.setId(id);
		}
		
		return city;
	}
}
