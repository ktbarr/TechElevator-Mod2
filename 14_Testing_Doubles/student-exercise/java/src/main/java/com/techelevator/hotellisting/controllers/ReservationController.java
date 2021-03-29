package com.techelevator.hotellisting.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.validation.Valid;

import com.techelevator.hotellisting.dao.ReservationDAO;
import com.techelevator.hotellisting.exception.HotelNotFoundException;
import com.techelevator.hotellisting.exception.ReservationNotFoundException;
import com.techelevator.hotellisting.models.Reservation;
import com.techelevator.hotellisting.service.ReservationNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationController {

    private ReservationDAO dao;

    private ReservationNotificationService notificationService;

    @Autowired
    public ReservationController(ReservationDAO dao, ReservationNotificationService notificationService) {
        this.dao = dao;
        this.notificationService = notificationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<Reservation> findAll() {
        return dao.findAll();
    }

    @RequestMapping(path = "/hotels/{hotelID}/reservations", method = RequestMethod.GET)
    public List<Reservation> list(@PathVariable String hotelID) {
        return dao.list(hotelID);
    }

    @RequestMapping(path = "/hotels/{hotelID}/reservations/{reservationID}", method = RequestMethod.GET)
    public Reservation get(@PathVariable String hotelID, @PathVariable int reservationID)
            throws ReservationNotFoundException {
        return dao.get(hotelID, reservationID);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/hotels/{hotelID}/reservations", method = RequestMethod.POST)
    public void create(@Valid @RequestBody Reservation reservation, @PathVariable String hotelID)
            throws HotelNotFoundException {
        dao.create(reservation, hotelID);
        notificationService.sendWelcomeNotification(reservation);
        long noOfDaysUntilCheckin = ChronoUnit.DAYS.between(LocalDate.now(), reservation.getCheckinDate());
        if (noOfDaysUntilCheckin <= 2) {
            notificationService.sendLateReservationNotification(reservation);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/hotels/{hotelID}/reservations/{reservationID}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Reservation reservation, @PathVariable String hotelID,
            @PathVariable int reservationID) throws ReservationNotFoundException {
        dao.update(reservation, hotelID, reservationID);
        notificationService.sendReservationUpdateNotification(reservation);
        long noOfDaysUntilCheckin = ChronoUnit.DAYS.between(LocalDate.now(), reservation.getCheckinDate());
        if (noOfDaysUntilCheckin <= 2) {
            notificationService.sendLateReservationNotification(reservation);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/hotels/{hotelID}/reservations/{reservationID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String hotelID, @PathVariable int reservationID)
            throws ReservationNotFoundException {
        dao.delete(hotelID, reservationID);
    }

}
