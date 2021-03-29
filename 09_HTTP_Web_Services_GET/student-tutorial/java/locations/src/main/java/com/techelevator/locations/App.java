package com.techelevator.locations;

import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

public class App 
{
	private static final String API_URL = "http://localhost:3000/locations";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) 
	{
		run();
	}

	private static void run() 
	{
	  Scanner scanner = new Scanner(System.in);
	  printGreeting();
	  
	  int menuSelection = 0;
	  
	  try 
	  {
		  menuSelection =Integer.parseInt(scanner.nextLine());
	  }
	  catch (NumberFormatException exception) 
	  {
		  System.out.println("Error parsing the input for menu selection.");
	  }
	  System.out.println("");
	  
	  if (menuSelection == 1) 
	  {
		  getLocations();
	  }
	  else if (menuSelection == 2) 
	  {
		  scanner.close();
		  System.exit(0);
	  }
	  else 
	  {
		  System.out.println("Invalid Selection");
	  }
	  
	  int id = 0;
	  
	  try 
	  {
		id = Integer.parseInt(scanner.nextLine());  
	  }
	  catch (NumberFormatException exception)
	  {
		  System.out.println("Error parsing the input for location id.");
	  }
	  
	  if (id > 0 && id <= locations.length) 
	  {
		  getLocationDetails(id);
	  }
	  else 
	  {
		  System.out.println("Invalid Location Id.");
	  }
  }

  private static void printGreeting() {
    System.out.println("");
    System.out.println("Welcome to Tech Elevator Locations. Please make a selection: ");
    System.out.println("1: List Tech Elevator Locations");
    System.out.println("2: Exit");
    System.out.println("");
    System.out.print("Please choose an option: ");
  }

  private static void printLocations(Location[] locations) {
    System.out.println("--------------------------------------------");
    System.out.println("Locations");
    System.out.println("--------------------------------------------");
    for (Location location : locations) {
      System.out.println(location.getId() + ": " + location.getName());
    }
    System.out.println("");
    System.out.print("Please enter a location id to get the details: ");
  }

  private static void printLocation(Location location) {
    System.out.println("");
    System.out.println("--------------------------------------------");
    System.out.println("Location Details");
    System.out.println("--------------------------------------------");
    System.out.println("Id: " + location.getId());
    System.out.println("Name: " + location.getName());
    System.out.println("Address: " + location.getAddress());
    System.out.println("City: " + location.getCity());
    System.out.println("State: " + location.getState());
    System.out.println("Zip: " + location.getZip());
  }
  
  private static void getLocations() 
  {
	  String url = API_URL;
	  Location[] locations = restTemplate.getForObject(url, Location[].class);
	  printLocations(locations);
  }
  
  private static void getLocationDetails(int id) 
  {
	  String url = API_URL + "/" + id;
	  Location location = restTemplate.getForObject(url, Location.class);
	  printLocation(location);
  }

}
