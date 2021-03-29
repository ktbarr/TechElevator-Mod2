package com.techelevator.hotellisting.models;

import java.math.BigDecimal;

public class Hotel {

    private String id;
    private String name;
    private Address address;
    private int stars;
    private int roomsAvailable;
    private BigDecimal costPerNight;
    private String coverImage;

    public Hotel() {

    }

    public Hotel(String id, String name, Address address, int stars, int numberOfRooms, double costPerNight, String cover) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stars = stars;
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
