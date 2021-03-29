package com.techelevator.hotellisting.service;

import com.techelevator.hotellisting.models.Reservation;
import org.springframework.stereotype.Component;

/**
 * This interface defines all of the notifications you must be able to send
 */
@Component
public interface ReservationNotificationService {

  /**
   * Sends welcome notification to user
   * @param reservation the reservation
   */
  public void sendWelcomeNotification(Reservation reservation);

  /**
   * Sends reservation update notification to user
   * @param reservation the reservation
   */
  public void sendReservationUpdateNotification(Reservation reservation);

  /**
   * Sends a notification to admin that a reservation was created or updated
   * with a checkin date <= 2 days from current date.
   * @param reservation the reservation
   */
  public void sendLateReservationNotification(Reservation reservation);

}
