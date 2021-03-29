package com.techelevator.hotellisting.service;

import com.techelevator.hotellisting.models.Review;

import org.springframework.stereotype.Component;

@Component
public class SmsNotificationService {

  public void send(Review review) {
    System.out.println("Sending SMS Notification for 1 star review off to the customer service department.");
  }

}
