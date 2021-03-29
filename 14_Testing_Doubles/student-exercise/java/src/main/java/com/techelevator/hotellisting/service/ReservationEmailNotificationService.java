package com.techelevator.hotellisting.service;

import com.techelevator.hotellisting.models.Reservation;

import org.springframework.stereotype.Component;

@Component
public class ReservationEmailNotificationService implements ReservationNotificationService {

  @Override
  public void sendWelcomeNotification(Reservation reservation) {
    System.out.println("Sending welcome email to: " + reservation.getEmail());
  }

  @Override
  public void sendReservationUpdateNotification(Reservation reservation) {
    System.out.println("Sending reservation update email to " + reservation.getEmail());
  }

  @Override
  public void sendLateReservationNotification(Reservation reservation) {
    System.out.println("Sending late reservation email to administrator");
  }

}
