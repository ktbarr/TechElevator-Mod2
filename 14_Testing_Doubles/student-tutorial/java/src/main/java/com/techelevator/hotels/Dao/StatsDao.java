package com.techelevator.hotels.Dao;

/**
 * Interface defining all of the methods any stats dao should implement
 */
public interface StatsDao {

    /**
     * Get all of the ratings
     * @return int[] of all ratings
     */
    public int[] getAllRatings();

    /**
     * Get all of the hotel cost per night
     * @return double[] Hotel costPerNight
     */
    public double[] getAllPrices();
}
