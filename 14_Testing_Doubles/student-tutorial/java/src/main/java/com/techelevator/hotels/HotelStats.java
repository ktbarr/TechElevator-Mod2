package com.techelevator.hotels;

import com.techelevator.hotels.Dao.StatsDao;

import java.util.Arrays;

public class HotelStats {

    private StatsDao statsDao;

    public HotelStats(StatsDao dao) {
        statsDao = dao;
    }

    /**
     * Gets the mean for all the review ratings in our system
     * @return double representing the avg rating
     */
    public double getRatingMean() {
        int[] stars = statsDao.getAllRatings();
        double total = 0.0;
        double mean = 0.0;

        if( stars.length > 0 ){
            for(int i = 0; i < stars.length; i++ ) {
                total += stars[i];
            }

            mean = total / (double) stars.length;
        }

        return mean;
    }

    /**
     * Gets the median for all the hotel prices in our system
     * @return
     */
    public double getHotelPriceMedian() {
        double[] prices = statsDao.getAllPrices();
        double median = 0.0;

        if( prices.length > 0 ){
            Arrays.sort(prices);

            if (prices.length % 2 != 0) {
                median = prices[prices.length/2];
            } else {
                median = (prices[prices.length/2] + prices[(prices.length / 2) - 1]) / 2.0;
            }
        }

        return median;
    }

}