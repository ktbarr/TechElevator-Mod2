package com.techelevator.hotellisting.controllers;

import java.util.List;
import javax.validation.Valid;
import com.techelevator.hotellisting.dao.ReviewDao;
import com.techelevator.hotellisting.exceptions.ReviewNotFoundException;
import com.techelevator.hotellisting.models.Review;
import com.techelevator.hotellisting.service.EmailNotificationService;
import com.techelevator.hotellisting.service.SmsNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels/{hotelID}/reviews")
@CrossOrigin
public class ReviewController {

    private ReviewDao dao;
    private EmailNotificationService emailNotificationService;
    private SmsNotificationService smsNotificationService;

    @Autowired
    public ReviewController(ReviewDao reviewDao, EmailNotificationService emailService,
            SmsNotificationService smsService) {
        dao = reviewDao;
        emailNotificationService = emailService;
        smsNotificationService = smsService;
    }

    /**
     *
     * @param hotelID the id of the hotel
     * @return A list of reviews for a given hotel
     * @throws ReviewNotFoundException
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Review> list(@PathVariable String hotelID) {
        return dao.getAllForHotel(hotelID);
    }

    /**
     *
     * @param review  the review
     * @param hotelID the hotelID that this review should belong to
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public void add(@Valid @RequestBody Review review, @PathVariable String hotelID) {
        review.setHotelID(hotelID);

        if (review.getStars() == 1) {
            smsNotificationService.send(review);
        }
        if (review.getStars() == 5) {
            emailNotificationService.send(review);
        }

        dao.create(review);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{reviewID}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Review review, @PathVariable String hotelID, @PathVariable String reviewID)
            throws ReviewNotFoundException {

        review.setHotelID(hotelID);
        review.setId(reviewID);
        dao.update(review);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{reviewID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String reviewID) {
        dao.delete(reviewID);
    }
}
