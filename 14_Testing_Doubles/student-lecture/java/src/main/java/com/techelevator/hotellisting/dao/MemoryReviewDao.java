package com.techelevator.hotellisting.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.techelevator.hotellisting.exceptions.ReviewNotFoundException;
import com.techelevator.hotellisting.models.Review;

import org.springframework.stereotype.Component;

/**
 * ReviewDao
 */
@Component
public class MemoryReviewDao implements ReviewDao {

    private List<Review> reviews = new ArrayList<>();

    public MemoryReviewDao() {
        setReviews();
    }

    @Override
    public List<Review> list() {
        return reviews;
    }

    @Override
    public void create(Review review) {
        review.setId(UUID.randomUUID().toString());
        reviews.add(review);
    }

    @Override
    public Review get(String id) throws ReviewNotFoundException {
        for (Review review : reviews) {
            if (review.getId().equals(id)) {
                return review;
            }
        }

        throw new ReviewNotFoundException();
    }

    @Override
    public List<Review> getAllForHotel(String hotelId) {
        List<Review> reviewsByHotel = new ArrayList<>();

        for (Review review : reviews) {
            if (review.getHotelID().equals(hotelId)) {
                reviewsByHotel.add(review);
            }
        }

        return reviewsByHotel;
    }

    @Override
    public void update(Review review) throws ReviewNotFoundException {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getId().equals(review.getId())) {
                reviews.set(i, review);
                return;
            }
        }

        throw new ReviewNotFoundException();
    }

    @Override
    public void delete(String id) {
        reviews.removeIf(review -> review.getId().equals(id));
    }

    private void setReviews() {
        reviews.add(new Review("1", "1", "What a great hotel!",
                "I thought this was a really great hotel and would stay again!", "John Smith", 4));
        reviews.add(new Review("2", "1", "Peaceful night sleep", "I had a really good night sleep and would stay again",
                "Kerry Gold", 3));
        reviews.add(new Review("3", "1", "Fancy!!!", "This place was super fancy", "Mike Unger", 4));
        reviews.add(new Review("4", "3", "Enjoyed my time here", "All around great hotel, no complaints here",
                "Frank Customer", 3));
        reviews.add(new Review("5", "5", "Great customer service", "The whole staff was amazing!", "Michelle Star", 5));
    }
}
