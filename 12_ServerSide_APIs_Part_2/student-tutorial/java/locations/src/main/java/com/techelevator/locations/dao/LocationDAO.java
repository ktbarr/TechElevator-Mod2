package com.techelevator.locations.dao;

import com.techelevator.locations.exception.LocationNotFoundException;
import com.techelevator.locations.models.Location;

import java.util.List;

public interface LocationDAO {

    List<Location> list();

    Location get(int id) throws LocationNotFoundException;

    Location create(Location location);

    Location update(Location location, int id) throws LocationNotFoundException;

    void delete(int id) throws LocationNotFoundException;

}
