package com.techelevator.hotellisting.service;

import com.techelevator.hotellisting.models.Review;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService {

  public void send(Review review) {
    System.out.println("Sending Email Notification for 5 star review off to the marketing department.");
  }

}
