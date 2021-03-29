package com.techelevator.reservations.models;

import java.util.ArrayList;
import java.util.List;

public class HotelFilters
{
	// put this in a seperate class so we can use in controller
	public static List<Hotel> filterByName(List<Hotel> hotels, String name) 
	{
		// new empty list
		List<Hotel> filteredList = new ArrayList<Hotel>();
		
		// loop through hotels, if found, add to new list
		for (Hotel hotel : hotels)
		{
			if(hotel.getName().toLowerCase().contains(name.toLowerCase())) 
			{
				filteredList.add(hotel);
			}
		}
		
		// return list
		return filteredList;
	}
	
	public static List<Hotel> filterByCost(List<Hotel> hotels, String name) 
	{
		// new empty list
		List<Hotel> filteredList = new ArrayList<Hotel>();
		
		// loop through hotels, if found, add to new list
		for (Hotel hotel : hotels)
		{
			if(hotel.getName().toLowerCase().contains(name.toLowerCase())) 
			{
				filteredList.add(hotel);
			}
		}
		
		// return list
		return filteredList;
	}

}
