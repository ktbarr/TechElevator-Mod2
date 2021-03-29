package com.techelevator.hotellisting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Reservation Not Found")
public class ReservationNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public ReservationNotFoundException() {
    super("Reservation Not Found");
  }
}
