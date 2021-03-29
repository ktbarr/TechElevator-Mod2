package com.techelevator.hotels.Dao;

/**
 * This class represents a DAO that will retrieve stats from out Sql Database. This is just an example and is
 * not actually connecting to a database but this represents one type of DAO that could retrieve data.
 */
public class SqlStatsDao implements StatsDao {

    /**
     * Returns an array of all the reviews in our system
     * @return int[] of stars
     */
    public int[] getAllRatings() {
        return new int[] {1,3,5,3,4,2,4,3,2,1,2,5,1,1,2,5};
    }

    public double[] getAllPrices() {
        return new double[] {99.99, 149.00, 49.00, 89.99, 199.99, 205.00, 299.99, 249.99, 175.00, 309.99, 83.00};
    }
}
