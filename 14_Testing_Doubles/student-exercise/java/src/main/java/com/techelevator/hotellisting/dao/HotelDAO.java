package com.techelevator.hotellisting.dao;

import com.techelevator.hotellisting.exception.HotelNotFoundException;
import com.techelevator.hotellisting.models.Hotel;

import java.util.List;

public interface HotelDAO {

    List<Hotel> list();

    void create(Hotel hotel);

    Hotel get(String id) throws HotelNotFoundException;

    void update(Hotel hotel, String id) throws HotelNotFoundException;

    void delete(String id);
}
