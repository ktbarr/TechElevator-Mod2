package com.techelevator.hotellisting.controllers;

import com.techelevator.hotellisting.dao.HotelDao;
import com.techelevator.hotellisting.exceptions.HotelNotFoundException;
import com.techelevator.hotellisting.models.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/hotels")
@CrossOrigin()
public class HotelController {

    private HotelDao dao;

    @Autowired
    public HotelController(HotelDao hotelDao) {
        dao = hotelDao;
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Hotel> list() {
        return dao.list();
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Hotel get(@PathVariable String id) throws HotelNotFoundException {
        return dao.get(id);
    }

    /**
     * /hotels/filter?state=oh&city=cleveland
     *
     * @param state the state to filter by
     * @param city  the city to filter by
     * @return a list of hotels that match the city & state
     */
    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public List<Hotel> filterByStateAndCity(@RequestParam String state, @RequestParam(required = false) String city) {

        List<Hotel> filteredHotels = new ArrayList<>();
        List<Hotel> hotels = list();

        // return hotels that match state
        for (Hotel hotel : hotels) {

            // if city was passed we don't care about the state filter
            if (city != null) {
                if (hotel.getAddress().getCity().toLowerCase().equals(city.toLowerCase())) {
                    filteredHotels.add(hotel);
                }
            } else {
                if (hotel.getAddress().getState().toLowerCase().equals(state.toLowerCase())) {
                    filteredHotels.add(hotel);
                }

            }
        }

        return filteredHotels;
    }

    /**
     * Creates a new hotel
     *
     * @param hotel the hotel to add to the list
     */
    @RequestMapping(path = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Hotel hotel) {
        dao.create(hotel);
    }

    /**
     * Updates a hotel based on the hotel id and the request body
     *
     * @param hotel the updated hotel
     * @param id    the id of the hotel that is getting updated
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Hotel hotel, @PathVariable String id) throws HotelNotFoundException {
        hotel.setId(id);
        dao.update(hotel);
    }

    /**
     * Removes a hotel from the list based on the id
     *
     * @param id the id of the hotel to remove
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        dao.delete(id);
    }

    /**
     *
     * @return a set of states
     */
    @RequestMapping(path = "/distinct-states", method = RequestMethod.GET)
    public Set<String> getDistinctStates() {
        Set<String> distinctStates = new TreeSet<>();
        List<Hotel> hotels = list();

        for (Hotel hotel : hotels) {
            distinctStates.add(hotel.getAddress().getState());
        }

        return distinctStates;
    }

    /**
     *
     * @param state the state to filter by
     * @return a list of cities
     */
    @RequestMapping(path = "/cities-by-state", method = RequestMethod.GET)
    public Set<String> getCitiesByState(@RequestParam String state) {
        Set<String> distinctCities = new TreeSet<>();
        List<Hotel> hotels = list();

        for (Hotel hotel : hotels) {
            if (hotel.getAddress().getState().toLowerCase().equals(state.toLowerCase())) {
                distinctCities.add(hotel.getAddress().getCity());
            }
        }

        return distinctCities;
    }
}
