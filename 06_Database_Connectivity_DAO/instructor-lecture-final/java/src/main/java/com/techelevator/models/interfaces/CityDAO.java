package com.techelevator.models.interfaces;

import java.util.List;

import com.techelevator.models.dto.City;

public interface CityDAO {

	public void save(City newCity);
	public City findCityById(long id);
	public List<City> findCityByCountryCode(String countryCode);
	public List<City> findCityByDistrict(String district);
	public void update(City city);
	public void delete(long id);
}
