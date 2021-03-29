package com.techelevator.hotels.services;

import com.techelevator.hotels.models.Hotel;
import com.techelevator.hotels.models.Reservation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class HotelService {

  private final String BASE_URL;
  private final RestTemplate restTemplate = new RestTemplate();
  private final ConsoleService console = new ConsoleService();

  public HotelService(String url) 
  {
    BASE_URL = url;
  }

  /**
   * Create a new reservation in the hotel reservation system
   *
   * @param newReservation
   * @return Reservation
   */
  public Reservation addReservation(String newReservation) 
  {
    // TODO: Implement method
	  // newReservation -> 1, Katie Barr, 2/1/2021, 2/5/2021, 2
	  
	  //1. Create the Reservation Object -- the body
	  Reservation reservation = makeReservation(newReservation);
	  
	  //2. call the API
	  		// 2.1 Build the request
	  
	  			// 2.1.1 Build the url -> http://localhost:3000/reservations
	  String url = BASE_URL + "reservations";
	  // 2.1.2 Build the body of the request
	  			// create the header
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.APPLICATION_JSON); // tell the server to expect JSON
	  			// this will be the body
	  HttpEntity<Reservation> entity = new HttpEntity<Reservation>(reservation, headers);
	  
	  // 2.2 execute the request
	  		// use this version so you can verify that it was successful
	  ResponseEntity<Reservation> response = restTemplate.postForEntity(url, entity, Reservation.class);
	  
	  if (response.getStatusCodeValue() >= 200 && response.getStatusCodeValue() < 300) 
	  {
		  // I know it worked...
		  return response.getBody();
	  }
	  else 
	  {
		  // log error to my error log file
	  }
	  
	  			// this version would just complete the task, but you would need to run the list again to see that it worked
	  // Reservation reservationFromApi = restTemplate.postForObject(url, entity, Reservation.class);
	  
	  return null;
  }

  /**
   * Updates an existing reservation by replacing the old one with a new
   * reservation
   *
   * @param CSV
   * @return
   */
  public Reservation updateReservation(String CSV) 
  {
    // TODO: Implement method
	  
	  // create the body
	  Reservation reservationToUpdate = makeReservation(CSV);
	  
	  // call the api
	  // build url
	  		// append the ID as the WHERE clause -> must have this or will update allllllll
	  String url = BASE_URL + "reservations/" + reservationToUpdate.getId();
	  
	  // headers
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.APPLICATION_JSON); // what data type to expect in the body
	  
	  HttpEntity<Reservation> entity = new HttpEntity<Reservation>(reservationToUpdate, headers);
	  
	  try 
	  {
		  // put does not return a response or object - this is why it's different than above
		  restTemplate.put(url, entity);
		  return reservationToUpdate;
	  }
	  catch (Exception e) 
	  {
		// TODO: handle exception
		  return null;
	  }
	  
  }

  /**
   * Delete an existing reservation
   *
   * @param id
   */
  public void deleteReservation(int id) 
  {
    // build the URL
	  String url = BASE_URL + "reservations/" + id;
	  
	  try
	{
		  restTemplate.delete(url);
	} 
	  catch (Exception e)
	{
		// log the error here
	}
  }

  /* DON'T MODIFY ANY METHODS BELOW */

  /**
   * List all hotels in the system
   *
   * @return
   */
  public Hotel[] listHotels() 
  {
    Hotel[] hotels = null;
    try 
    {
      hotels = restTemplate.getForObject(BASE_URL + "hotels", Hotel[].class);
    } 
    catch (RestClientResponseException ex) 
    {
      // handles exceptions thrown by rest template and contains status codes
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } 
    catch (ResourceAccessException ex) 
    {
      // i/o error, ex: the server isn't running
      console.printError(ex.getMessage());
    }
    return hotels;
  }

  /**
   * Get the details for a single hotel by id
   *
   * @param id
   * @return Hotel
   */
  public Hotel getHotel(int id) 
  {
    Hotel hotel = null;
    try 
    {
      hotel = restTemplate.getForObject(BASE_URL + "hotels/" + id, Hotel.class);
    } 
    catch (RestClientResponseException ex) 
    {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } 
    catch (ResourceAccessException ex) 
    {
      console.printError(ex.getMessage());
    }
    return hotel;
  }

  /**
   * List all reservations in the hotel reservation system
   *
   * @return Reservation[]
   */
  public Reservation[] listReservations() 
  {
    Reservation[] reservations = null;
    try 
    {
      reservations = restTemplate.getForObject(BASE_URL + "reservations", Reservation[].class);
    } 
    catch (RestClientResponseException ex) 
    {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } 
    catch (ResourceAccessException ex) 
    {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * List all reservations by hotel id.
   *
   * @param hotelId
   * @return Reservation[]
   */
  public Reservation[] listReservationsByHotel(int hotelId) 
  {
    Reservation[] reservations = null;
    try 
    {
      reservations = restTemplate.getForObject(BASE_URL + "hotels/" + hotelId + "/reservations", Reservation[].class);
    } 
    catch (RestClientResponseException ex) 
    {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } 
    catch (ResourceAccessException ex) 
    {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * Find a single reservation by the reservationId
   *
   * @param reservationId
   * @return Reservation
   */
  public Reservation getReservation(int reservationId) 
  {
    Reservation reservation = null;
    try 
    {
      reservation = restTemplate.getForObject(BASE_URL + "reservations/" + reservationId, Reservation.class);
    } 
    catch (RestClientResponseException ex) 
    {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservation;
  }

  private Reservation makeReservation(String CSV) {
    String[] parsed = CSV.split(",");

    // invalid input (
    if (parsed.length < 5 || parsed.length > 6) {
      return null;
    }

    // Add method does not include an id and only has 5 strings
    if (parsed.length == 5) {
      // Create a string version of the id and place into an array to be concatenated
      String[] withId = new String[6];
      String[] idArray = new String[] { new Random().nextInt(1000) + "" };
      // place the id into the first position of the data array
      System.arraycopy(idArray, 0, withId, 0, 1);
      System.arraycopy(parsed, 0, withId, 1, 5);
      parsed = withId;
    }

    return new Reservation(Integer.parseInt(parsed[0].trim()), Integer.parseInt(parsed[1].trim()), parsed[2].trim(),
        parsed[3].trim(), parsed[4].trim(), Integer.parseInt(parsed[5].trim()));
  }

}
