# Testing Doubles Exercise

In this exercise, you'll take a hotel reservations system from a previous exercise and add some unit tests to it.

## Project Overview

If you have a chance, spend some time looking through the project and getting familiar with the code. Most of this should look pretty familiar to you. The only new items are the two classes in the `com.techelevator.hotellisting.service` package. 

The main focus of this exercise is the `ReservationController`. The controller has two dependencies: `ReservationDAO` and `ReservationNotificationService`.

## Reservation Controller Test

If you look at the `ReservationControllerTest` class in `/src/test/ava/com/techelevator/hotellisting/controller`, you'll see the starting code that you'll work with. Notice that there are some sample hotels and reservations created for you that you can use in your tests.

```java
/*
    * Creates an instance of the ReservationController and calls methods to set up hotel and reservation data.
    * This method should run once before all of the tests and:
    *   create a new instance of the Reservation Controller
    *   call createHotels()
    *   call createReservations()
    */
public void setup() {

}

/*
    * Creates test data for hotels
    */
private void createHotels() {
    hotels.add(new Hotel("ABCD1234", "TEST HOTEL", new Address("1234 Main St", "", "Cleveland", "OH", "44113"), 3, 15, 99, ""));
    hotels.add(new Hotel("EFGH5678", "ANOTHER HOTEL", new Address("4321 Main St", "", "Cleveland", "OH", "44113"), 3, 15, 99, ""));
}

/*
    * Creates test data for reservations
    */
private void createReservations() {
    LocalDate now = LocalDate.now();
    reservations.add(new Reservation(1, hotels.get(0), "John Smith", now, now.plusDays(3), 2, "johnsmith@gmail.com"));
    reservations.add(new Reservation(2, hotels.get(0), "Anna Blair", now, now.plusDays(1), 1, "annablair@gmail.com"));
    reservations.add(new Reservation(3, hotels.get(1), "Sam Smith", now, now.plusDays(7), 1, "samsmith@gmail.com"));
}
```

Your first task is to create an instance of the `ReservationController` and assign it to the variable `controller`. The `ReservationController`s constructor takes two arguments, so you need to figure out how to satisfy those. This should be done in the `setup` method and run once *before* all other tests.

```java
public class ReservationControllerTest {

    private ReservationController controller;
    private List<Hotel> hotels = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    /*
        * Creates an instance of the ReservationController and calls methods to set up hotel and reservation data.
        * This method should run once before all of the tests and:
        *   create a new instance of the Reservation Controller
        *   call createHotels()
        *   call createReservations()
        */
    public void setup() {

    }

    // ...

}
```

Once that works and your code compiles, you can move on to writing each of the unit tests.

## Exercise Instructions

The `ReservationControllerTest` class has 12 tests that you need to write. Some of the test method signatures have been written for you; others might be missing an annotation or have no method body at all. Each test method starts with a description that describes what the test should test. Remember to write tests that are very discrete and only test one piece of functionality. When completed, you should end up with a total of 12 tests.

## Completed Exercise

When you have completed this exercise, you should end up with 12 unit tests. Your code should compile without errors, and all tests should pass.
