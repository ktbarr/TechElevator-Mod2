package com.techelevator.hotellisting.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Reservation {

  private int id;
  private Hotel hotel;
  @NotBlank(message = "Please provide a name for this reservation")
  private String fullName;
  @NotNull(message = "Please enter a valid checkin date")
  @FutureOrPresent(message = "The checkin date must be in the future")
  private LocalDate checkinDate;
  @NotNull(message = "Please enter a valid checkout date")
  @FutureOrPresent(message = "The checkout date must be in the future")
  private LocalDate checkoutDate;
  @Min(value = 1, message = "The number of guests for this reservation must be at least 1")
  @Max(value = 5, message = "The maximum number of guests per room is 5")
  private int guests;
  @NotBlank(message = "Please provide an email address for this reservation")
  @Email(message = "Please provide a valid email address for this reservation")
  private String email;

  public Reservation(int id, Hotel hotel, String fullName, LocalDate checkinDate, LocalDate checkoutDate, int guests,
      String email) {
    this.id = id;
    this.hotel = hotel;
    this.fullName = fullName;
    this.checkinDate = checkinDate;
    this.checkoutDate = checkoutDate;
    this.guests = guests;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public LocalDate getCheckinDate() {
    return checkinDate;
  }

  public void setCheckinDate(LocalDate checkinDate) {
    this.checkinDate = checkinDate;
  }

  public LocalDate getCheckoutDate() {
    return checkoutDate;
  }

  public void setCheckoutDate(LocalDate checkoutDate) {
    this.checkoutDate = checkoutDate;
  }

  public int getGuests() {
    return guests;
  }

  public void setGuests(int guests) {
    this.guests = guests;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Reservation [fullName=" + fullName + ", hotel=" + hotel + "]";
  }

}
