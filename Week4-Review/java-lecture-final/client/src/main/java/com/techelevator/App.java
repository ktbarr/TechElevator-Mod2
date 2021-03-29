package com.techelevator;

import com.techelevator.application.VendingMachine;

public class App
{
    private static final String API_BASE_URL = "http://localhost:8080/";

    public static void main(String[] args)
    {
        VendingMachine vendingMachine = new VendingMachine(API_BASE_URL);
        vendingMachine.run();
    }
}
