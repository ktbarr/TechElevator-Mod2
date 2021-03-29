package com.techelevator.hotellisting.models;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class Review {

    private String id;
    private String hotelID;
    private String title;
    private String review;

    @NotBlank(message = "The author cannot be blank")
    private String author;

    @Range(min = 1, max = 5, message = "The stars must be between 1 and 5")
    private int stars;

    public Review() {

    }

    public Review(String id, String hotelID, String title, String review,
            String author, int stars) {
        this.id = id;
        this.hotelID = hotelID;
        this.title = title;
        this.review = review;
        this.author = author;
        this.stars = stars;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Review{" + "title='" + title + '\'' + ", review='" + review
                + '\'' + ", author='" + author + '\'' + ", stars=" + stars
                + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((hotelID == null) ? 0 : hotelID.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((review == null) ? 0 : review.hashCode());
        result = prime * result + stars;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (hotelID == null) {
            if (other.hotelID != null)
                return false;
        } else if (!hotelID.equals(other.hotelID))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (review == null) {
            if (other.review != null)
                return false;
        } else if (!review.equals(other.review))
            return false;
        if (stars != other.stars)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }
}
