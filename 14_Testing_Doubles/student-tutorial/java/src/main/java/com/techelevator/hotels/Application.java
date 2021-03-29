package com.techelevator.hotels;

import com.techelevator.hotels.Dao.StatsDao;
import com.techelevator.hotels.Dao.SqlStatsDao;

public class Application {

    public static void main(String[] args) {
        StatsDao dao = new SqlStatsDao();
        HotelStats hotelStats = new HotelStats(dao);

        double meanRating = hotelStats.getRatingMean();
        System.out.println("Average Rating: " + meanRating);

        double medianPrice = hotelStats.getHotelPriceMedian();
        System.out.println("Median Price: " + medianPrice);
    }
}
