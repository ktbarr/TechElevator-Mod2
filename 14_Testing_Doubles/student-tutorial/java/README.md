# Testing Doubles Tutorial

In this tutorial, you'll continue working on the hotels application. This time, you've been assigned to a new team. There is a new project that does statistical analysis on the hotel data. 

## Project Overview

If you look in your `student-tutorial` folder, you should have the starting code for this tutorial. This is a simple console application that contains the following classes:


- `Application.java`: The main entry point for the application.
- `HotelStats`: The main class that performs the statistical analysis of the data.
- `StatsDao`: The Statistics DAO Interface.
- `SqlStatsDao`: A DAO that can retrieve statistics from a database.

If you look at the Maven Build (`pom.xml`), you should see the following:

```java
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>3.2.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

The dependencies for `JUnit` & `Mockito` have already been declared, so you should have everything you need to write your tests.

## Creating and Writing Unit Tests

If you look under `/src/test/java/com/techelevator/hotels`, you'll notice there are currently no tests. The first thing to do is right click on that package and create a new `Java` class called `HotelStatsTest`. This is done by convention and includes the class you're testing against along with the suffix `Test`.

```java
package com.techelevator.hotels;

public class HotelStatsTest {

}
```

Next, set up the variables that you'll need to write your tests:

```java
public class HotelStatsTest {
    private HotelStats stats;
    private StatsDao dao;
}
```

You'll want to create an instance of the `HotelStats` object once before any of the tests run. To do this, use JUnit's `@Before` lifecycle method, as shown below:

```java
public class HotelStatsTest {

    private HotelStats stats;
    private StatsDao dao;

    @Before
    public void setup() {
      // ...
    }
}
```

There is one thing to consider when creating an instance of the `HotelStats` class. The constructor for `HotelStats` takes in an argument of type `StatsDao`. You know from the project overview that there is an implementation of that interface called `SqlStatsDao`. You could create an instance of that class:

```java
public class HotelStatsTest {

    private HotelStats stats;
    private StatsDao dao;

    @Before
    public void setup() {
        dao = new SqlStatsDao();
        stats = new HotelStats(dao);
    }
}
```

However, if you do this, you'll have some problems. First, this violates the idea of a unit test, which is designed to test a single "unit" of functionality. Secondly, unit tests are supposed to be fast, and if you introduce a database that is a dependency, your tests may slow down. If you create a new instance of `SqlStatsDao`, you're now testing that along with your `HotelStats` class, and this isn't what you want to do. 

Instead, you can mock the `SqlStatsDao` using `Mockito` and pass that mocked object into your `HotelStats` constructor. Now, you have control over that mocked object, and when methods are called, you can provide your own data.

```java
package com.techelevator.hotels;

import com.techelevator.hotels.Dao.SqlStatsDao;
import com.techelevator.hotels.Dao.StatsDao;
import org.junit.Before;

import static org.mockito.Mockito.*;

public class HotelStatsTest {

    private HotelStats stats;
    private StatsDao dao = mock(SqlStatsDao.class);

    @Before
    public void setup() {
        stats = new HotelStats(dao);
    }

}
```

With all of the setup completed, you can now write your individual tests.


### Mean Rating Tests

The first method in your `HotelStats` class that you need to write tests for is `getRatingMean()`. Mean is the average, so what you'll do here is add all of the hotel ratings (1-5) and divide that by the number of ratings.

```java
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
```

In your tests, you'll want to test for the following scenarios:

- When the data set is empty
- When the data set contains all of the same number, it should return that number  
- When the data set contains different numbers, it should return the correct mean

To write your first test, start with the `@Test` annotation followed by a `public` method that doesn't return anything. The name of the test method should be descriptive, and by convention, starts with the word `test`.

```java
@Test
public void testRatingMeanIsZeroWithEmptyDataSet() {

}
```

Tests can be divided up into three sections: `Arrange`, `Act`, and `Assert`. It's good practice to start with this structure.

```java
@Test
public void testRatingMeanIsZeroWithEmptyDataSet() {
    // Arrange

    // Act

    // Assert
}
```

With the `SqlStatsDao` class mocked out, you can provide your own data when a method is called. When `getAllRatings()` is called in your first test, you'll want to have an empty data set. To do so, set that up in the `Arrange` section:

```java
@Test
public void testRatingMeanIsZeroWithEmptyDataSet() {
    // Arrange
    int[] dataSet = new int[]{};

    // Act

    // Assert
}
```

In `Act`, you can use `Mockito`s `when().thenReturn()` to provide your own return data when that method is called.

```java
@Test
public void testRatingMeanIsZeroWithEmptyDataSet() {
    // Arrange
    int[] dataSet = new int[]{};

    // Act
    when(dao.getAllRatings()).thenReturn(dataSet);

    // Assert
}
```

Finally, you want to `Assert` that the expected result is `0.00` when `stats.getRatingMean()` is called with an empty data set:

```java
@Test
public void testRatingMeanIsZeroWithEmptyDataSet() {
    // Arrange
    int[] dataSet = new int[]{};

    // Act
    when(dao.getAllRatings()).thenReturn(dataSet);

    // Assert
    assertEquals("Mean of empty array should be 0", 0.00,
            stats.getRatingMean(), 0);
}
```

That is the first of the three methods you need to write for this method. The other two are:

When the data set contains all of the same number, it should return that number
  - Name: testRatingMeanIsCorrectWithAllTheSameNumbers
  - Use the data set {2,2,2,2,2}
  - Expect the Mean to be 2.0

When the data set contains different numbers, it should return the correct mean
  - Name: testRatingMeanIsCorrectWhenDataSetContainsDifferentNumbers
  - Use the data set {1,2,2,3,5,3,4,4,2,1}
  - Expect the Mean to be 2.7

Before looking at the solutions, try to do these on your own:

```java
@Test
public void testRatingMeanIsCorrectWithAllTheSameNumbers() {
    // Arrange
    int[] dataSet = new int[] {2, 2, 2, 2, 2};

    // Act
    when(dao.getAllRatings()).thenReturn(dataSet);

    // Assert
    assertEquals("Mean of array of all 2s should be 2", 2.00,
            stats.getRatingMean(), 0);
}

@Test
public void testRatingMeanIsCorrectWhenDataSetContainsDifferentNumbers() {
    // Arrange
    int[] dataSet = new int[] {1, 2, 2, 3, 5, 3, 4, 4, 2, 1};

    // Act
    when(dao.getAllRatings()).thenReturn(dataSet);

    // Assert
    assertEquals("Mean of array should be average of array", 2.7,
            stats.getRatingMean(), 0);
}
```

### Median Price Tests

In statistics, the median value is the number in the middle of the data set. Here's how you can calculate it:

> "If there is an odd amount of numbers, the median value is the number that is in the middle, with the same amount of numbers below and above. If there is an even amount of numbers in the list, the middle pair must be determined, added together, and divided by two to find the median value" (Ganti, 2019).

If you look in `HotelStats`, you'll see the method that calculates the `Median` Hotel Price:

```java
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
```

There are three tests that you need to write here:

When the data set is empty
  - Name: testPriceMedianIsZeroWithEmptyDataSet
  - The data set should be an empty `double[]`
  - You should expect the result to be 0.00

When the data set contains an odd number of values
  - When the data set has an odd number of values, it should be the middle of the sorted data set
  - Name: testPriceMedianWithDataSetLengthOdd
  - Use the data set double[] {99.99,149.99,89.99,129.99,199.99}
  - Expect the Median to be 129.99

When the data set contains an even number of values
  - When the data set has an even number, the median is the sum of the middle 2 / 2.0
  - Name: testPriceMedianWithDataSetLengthEven
  - Use the data set double[] {99.99,149.99,89.99,129.99,199.99, 179.99}
  - Expect the Median to be 139.99

Try to write these tests before looking at the solutions below:

```java
@Test
public void testPriceMedianIsZeroWithEmptyDataSet() {
    // Arrange
    double[] dataSet = new double[] {};

    // Act
    when(dao.getAllPrices()).thenReturn(dataSet);

    // Assert
    assertEquals("Median of empty array should be 0", 0.00,
            stats.getHotelPriceMedian(), 0);
}

@Test
public void testPriceMedianWithDataSetLengthOdd() {
    // Arrange
    double[] dataSet = new double[] {99.99, 149.99, 89.99, 129.99, 199.99};

    // Act
    when(dao.getAllPrices()).thenReturn(dataSet);

    // Assert
    assertEquals("Median of odd number array should be middle number", 129.99,
            stats.getHotelPriceMedian(), 0);
}

@Test
public void testPriceMedianWithDataSetLengthEven() {
    // Arrange
    double[] dataSet =
            new double[] {99.99, 149.99, 89.99, 129.99, 199.99, 179.99};

    // Act
    when(dao.getAllPrices()).thenReturn(dataSet);

    // Assert
    assertEquals("Median of even number array is middle two divided by 2",
            139.99, stats.getHotelPriceMedian(), 0);
}
```

### Sources

Ganti, Akhilesh. *Median*. Retrieved from https://www.investopedia.com/terms/m/median.asp.
