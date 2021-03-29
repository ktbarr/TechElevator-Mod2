package com.techelevator.hotellisting.controller;

import com.techelevator.hotellisting.exception.HotelNotFoundException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.hotellisting.controllers.ReservationController;
import com.techelevator.hotellisting.exception.ReservationNotFoundException;
import com.techelevator.hotellisting.models.Address;
import com.techelevator.hotellisting.models.Hotel;
import com.techelevator.hotellisting.models.Reservation;

public class ReservationControllerTest {

    private ReservationController controller;
    private List<Hotel> hotels;
    private List<Reservation> reservations;
    /*
     * Creates an instance of the ReservationController and calls methods to setup
     * hotel and reservation data. This method should run once before all of the
     * tests and: create a new instance of the Reservation Controller call
     * createHotels() call createReservations()
     */
    public void setup() {
        hotels = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    /*
     * Creates test data for hotels
     */
    private void createHotels() {
        hotels.add(new Hotel("ABCD1234", "TEST HOTEL", new Address("1234 Main St", "", "Cleveland", "OH", "44113"), 3, 15, 99, ""));
        hotels.add(new Hotel("EFGH5678", "ANOTHER HOTEL", new Address("4321 Main St", "", "Cleveland", "OH", "44113"), 3, 15, 99, ""));
    }

    /*
     * Creates test data for reservations
     */
    private void createReservations() {
        LocalDate now = LocalDate.now();
        reservations.add(new Reservation(1, hotels.get(0), "John Smith", now, now.plusDays(3), 2, "johnsmith@gmail.com"));
        reservations.add(new Reservation(2, hotels.get(0), "Anna Blair", now, now.plusDays(1), 1, "annablair@gmail.com"));
        reservations.add(new Reservation(3, hotels.get(1), "Sam Smith", now, now.plusDays(7), 1, "samsmith@gmail.com"));
    }

    /*
     * Does the return of the DAO method match what is getting returned by the
     * controller?
     */
    @Test
    public void testFindAllReservationsReturnsAllReservations() {
        // Arrange

        // Act

        // Assert
    }

    /*
     * Does the return of the DAO method match what is getting returned by the
     * controller?
     */
    @Test
    public void testListReturnsReservationsByHotelMatchesDaoReturn() {
        // Arrange

        // Act

        // Assert
    }

    /*
     * Does the id given to the controller method get passed to the DAO?
     */
    @Test
    public void testListShouldPassHotelIdToDAO() {
        // Arrange

        // Act

        // Assert
    }

    /*
     * Does the return of the DAO method match what is getting returned by the
     * controller?
     */
    public void testGetReturnsReservationMatchesDaoReturn() throws ReservationNotFoundException {
        // Arrange

        // Act

        // Assert
    }

    /**
     * Does the hotelID & registrationID given to the controller method get passed
     * to the DAO?
     */
    public void testGetShouldPassHotelAndReservationIdToDAO() throws ReservationNotFoundException {
        // Arrange

        // Act

        // Assert
    }

    /*
     * Does the create method send a welcome notification to user?
     */
    public void testCreateCallsNotificationServiceSendWelcomeNotification() throws HotelNotFoundException {
        // Arrange

        // Act

        // Assert
    }

    /*
     * If reservation is within 2 days does the create method send late reservation
     * notification to administrator?
     */
    public void testCreateReservationWithinTwoDaysSendsLateReservationNotification() throws HotelNotFoundException {

    }

    /*
     * Does the reservation object and hotelID get passed to DAO?
     */
    public void testCreateShouldPassReservationAndHotelIdToDAO() throws HotelNotFoundException {

    }

    /*
     * Does the update method send a reservation update notification to the user?
     */
    public void testUpdateCallsNotificationServiceSendsReservationUpdateNotification()
            throws HotelNotFoundException, ReservationNotFoundException {

    }

    /*
     * If reservation is within 2 days does the update method send late reservation
     * notification to administrator?
     */


    /*
     * Does the reservation object passed to the controller go to the DAO?
     */


    /*
     * Does the hotelID & reservationID given to the controller method get passed to
     * the DAO?
     */

}
