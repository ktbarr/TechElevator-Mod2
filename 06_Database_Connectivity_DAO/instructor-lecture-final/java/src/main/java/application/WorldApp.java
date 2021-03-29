package application;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.models.dao.JDBCCityDAO;
import com.techelevator.models.dto.City;
import com.techelevator.models.interfaces.CityDAO;

public class WorldApp
{
	CityDAO cityDao;
	
	public WorldApp()
	{
		// create data source
		BasicDataSource worldDataSource = new BasicDataSource();
		worldDataSource.setUrl("jdbc:postgresql://localhost:5432/world");
		worldDataSource.setUsername("postgres");
		worldDataSource.setPassword("postgres1");
		
		//create the DAO based on the data source
		cityDao = new JDBCCityDAO(worldDataSource);
	}

	public void run()
	{
		saveNewCity();
	}
	
	public void saveNewCity()
	{

		// assume that we got data from the user
		
		City smallville = new City();
		smallville.setCountryCode("USA");
		smallville.setDistrict("Kansas");
		smallville.setName("Smallville");
		smallville.setPopulation(42080);
		
		cityDao.save(smallville);
		
		System.out.println(smallville.getId() + " " + smallville.getName());
	}
	
	public void displayCitiesByCountry()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a country: ");
		String country = scanner.nextLine().toUpperCase();
		
		List<City> cities = cityDao.findCityByCountryCode(country);
		
		System.out.println("Cities in: " + country);
		for (City city : cities)
		{
			System.out.println(city.getName());
		}		
		
	}
	
	public void displayCitiesByState()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a state: ");
		String state = scanner.nextLine();
		
		List<City> cities = cityDao.findCityByDistrict(state);
		
		System.out.println("Cities in: " + state);
		for (City city : cities)
		{
			System.out.println(city.getName());
		}		
		
	}
}
