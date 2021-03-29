package com.techelevator.hotellisting.dao;

import java.util.List;
import com.techelevator.hotellisting.exceptions.HotelNotFoundException;
import com.techelevator.hotellisting.models.Hotel;

/**
 * HotelDao
 */
public interface HotelDao {

    List<Hotel> list();

    void create(Hotel hotel);

    Hotel get(String id) throws HotelNotFoundException;

    void update(Hotel hotel) throws HotelNotFoundException;

    void delete(String id);

}
