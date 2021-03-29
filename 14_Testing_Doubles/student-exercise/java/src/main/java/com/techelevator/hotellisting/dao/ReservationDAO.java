package com.techelevator.hotellisting.dao;

import java.util.List;

import com.techelevator.hotellisting.exception.HotelNotFoundException;
import com.techelevator.hotellisting.exception.ReservationNotFoundException;
import com.techelevator.hotellisting.models.Reservation;

public interface ReservationDAO {

  List<Reservation> findAll();

  List<Reservation> list(String hotelID);

  Reservation get(String hotelID, int reservationID) throws ReservationNotFoundException;

  void create(Reservation reservation, String hotelID) throws HotelNotFoundException;

  void update(Reservation reservation, String hotelID, int reservationID) throws ReservationNotFoundException;

  void delete(String hotelID, int reservationID);

}
