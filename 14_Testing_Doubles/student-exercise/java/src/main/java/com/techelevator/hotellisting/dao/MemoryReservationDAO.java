package com.techelevator.hotellisting.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.hotellisting.exception.HotelNotFoundException;
import com.techelevator.hotellisting.exception.ReservationNotFoundException;
import com.techelevator.hotellisting.models.Hotel;
import com.techelevator.hotellisting.models.Reservation;

import org.springframework.stereotype.Component;

@Component
public class MemoryReservationDAO implements ReservationDAO {

  private List<Reservation> reservations;
  private HotelDAO hotelDAO;

  public MemoryReservationDAO() {
    hotelDAO = new MemoryHotelDAO();
    init();
  }

  private void init() {
    LocalDate now = LocalDate.now();
    List<Hotel> hotels = hotelDAO.list();

    reservations = new ArrayList<>();
    reservations.add(new Reservation(getMaxIdPlusOne(), hotels.get(0), "John Smith", now, now.plusDays(3), 2, "johnsmith@gmail.com"));
    reservations.add(new Reservation(getMaxIdPlusOne(), hotels.get(0), "Anna Blair", now, now.plusDays(1), 1, "annablair@gmail.com"));
    reservations.add(new Reservation(getMaxIdPlusOne(), hotels.get(1), "Sam Smith", now, now.plusDays(7), 1, "samsmith@gmail.com"));
  }

  public List<Reservation> findAll() {
    return reservations;
  }

  @Override
  public List<Reservation> list(String hotelID) {

    List<Reservation> hotelReservations = new ArrayList<>();
    for (Reservation r : reservations) {
      if (r.getHotel().getId().equals(hotelID)) {
        hotelReservations.add(r);
      }
    }

    return hotelReservations;
  }

  @Override
  public Reservation get(String hotelID, int reservationID) throws ReservationNotFoundException {
    for (Reservation res : reservations) {
      if (res.getHotel().getId().equals(hotelID) && res.getId() == reservationID) {
        return res;
      }
    }
    throw new ReservationNotFoundException();
  }

  @Override
  public void create(Reservation reservation, String hotelID) throws HotelNotFoundException {
    reservation.setId(getMaxIdPlusOne());
    reservation.setHotel(hotelDAO.get(hotelID));
    this.reservations.add(reservation);
  }

  @Override
  public void update(Reservation reservation, String hotelID, int reservationID) throws ReservationNotFoundException {

    for (int i = 0; i < reservations.size(); i++) {
      if (reservations.get(i).getId() == reservationID) {
        reservations.set(i, reservation);
        return;
      }
    }

    throw new ReservationNotFoundException();
  }

  @Override
  public void delete(String hotelID, int reservationID) {
    reservations.removeIf(r -> r.getHotel().getId().equals(hotelID) && r.getId() == reservationID);
  }

  /**
   * finds the max id in the list of reservations and returns it
   *
   * @return int the max id
   */
  private int getMaxID() {
    int maxID = 0;
    for (Reservation r : reservations) {
      if (r.getId() > maxID) {
        maxID = r.getId();
      }
    }
    return maxID;
  }

  /**
   * Adds 1 to the max id and returns it
   *
   * @return
   */
  private int getMaxIdPlusOne() {
    return getMaxID() + 1;
  }

}
