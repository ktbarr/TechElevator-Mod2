package com.techelevator.hotellisting.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.techelevator.hotellisting.exceptions.HotelNotFoundException;
import com.techelevator.hotellisting.models.Address;
import com.techelevator.hotellisting.models.Hotel;

import org.springframework.stereotype.Component;

/**
 * MemoryHotelDao
 */
@Component
public class MemoryHotelDao implements HotelDao {

    private List<Hotel> hotels = new ArrayList<>();

    public MemoryHotelDao() {
        setHotels();
    }

    @Override
    public Hotel get(String id) throws HotelNotFoundException {
        for (Hotel hotel : hotels) {
            if (hotel.getId().equals(id)) {
                return hotel;
            }
        }
        throw new HotelNotFoundException();
    }

    @Override
    public List<Hotel> list() {
        return hotels;
    }

    @Override
    public void create(Hotel hotel) {
        this.hotels.add(hotel);
    }

    @Override
    public void update(Hotel hotel) throws HotelNotFoundException {
        List<Hotel> hotels = list();

        for (int i = 0; i < hotels.size() - 1; i++) {
            if (hotels.get(i).getId().equals(hotel.getId())) {
                hotels.set(i, hotel);
                return;
            }
        }

        throw new HotelNotFoundException();
    }

    @Override
    public void delete(String id) {
        hotels.removeIf(hotel -> hotel.getId().equals(id));
    }

    private void setHotels() {
        hotels.add(new Hotel(UUID.randomUUID().toString(), "Aloft Cleveland",
                new Address("1111 W 10th St", "", "Cleveland", "OH", "44113"), 3, 48, 274, "aloft-cleveland.webp"));
        hotels.add(new Hotel(UUID.randomUUID().toString(), "Hilton Cleveland Downtown",
                new Address("100 Lakeside Ave", "", "Cleveland", "OH", "44114"), 4, 12, 287, "hilton-cleveland.webp"));
        hotels.add(new Hotel(UUID.randomUUID().toString(), "Metropolitan at the 9",
                new Address("2017 E 9th St", "", "Cleveland", "OH", "44115"), 4, 22, 319,
                "metropolitan-cleveland.webp"));
        hotels.add(new Hotel(UUID.randomUUID().toString(), "The Westin Pittsburgh",
                new Address("1000 Penn Ave", "", "Pittsburgh", "PA", "15222"), 4, 60, 131, "westin-pittsburgh.webp"));
        hotels.add(new Hotel(UUID.randomUUID().toString(), "Hilton Columbus Downtown",
                new Address("401 N High St", "", "Columbus", "OH", "43215"), 4, 43, 190, "hilton-columbus.webp"));
        hotels.add(new Hotel(UUID.randomUUID().toString(), "The Summit A Dolce Hotel",
                new Address("5345 Medpace Way", "", "Cincinnati", "OH", "43215"), 4, 43, 218,
                "summit-cincinnati.webp"));
        hotels.add(new Hotel(UUID.randomUUID().toString(), "Greektown Detroit",
                new Address("1200 St Antoine St", "", "Detroit", "MI", "48226"), 4, 75, 185, "greektown-detroit.webp"));
    }

}
