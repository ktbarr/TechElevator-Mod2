package com.techelevator.hotellisting.dao;

import java.util.List;
import com.techelevator.hotellisting.exceptions.ReviewNotFoundException;
import com.techelevator.hotellisting.models.Review;

/**
 * ReviewDao
 */

public interface ReviewDao {

    List<Review> list();

    void create(Review review);

    List<Review> getAllForHotel(String hotelId);

    Review get(String id) throws ReviewNotFoundException;

    void update(Review review) throws ReviewNotFoundException;

    void delete(String id);

}