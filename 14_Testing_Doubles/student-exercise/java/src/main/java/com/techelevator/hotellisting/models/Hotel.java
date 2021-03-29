package com.techelevator.hotellisting.models;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Hotel {

    private String id;
    @NotBlank(message = "Please enter a name")
    private String name;
    @Valid
    private Address address;
    private int rating;
    @Min(value = 0, message = "Please enter a positive number of rooms available.")
    private int roomsAvailable;
    @DecimalMin(value = "1.0", message = "Please enter a number greater than 0 for cost per night.")
    private BigDecimal costPerNight;
    private String coverImage;

    public Hotel(String id, String name, Address address, int rating, int numberOfRooms, double costPerNight,
            String cover) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.roomsAvailable = numberOfRooms;
        this.costPerNight = new BigDecimal(costPerNight);
        this.coverImage = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    public BigDecimal getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(BigDecimal costPerNight) {
        this.costPerNight = costPerNight;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
