package com.assessment.search;

import com.assessment.exceptions.CSVReaderException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantsSearchTest {
    RestaurantsSearch search;

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    InputStream originalIn = System.in;

    @BeforeEach
    void setup() throws CSVReaderException {
        search = new RestaurantsSearch();
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
    @Test
    void searchRestaurantsWithNoCuisine_shouldReturnFiveExpectedBestMatches() throws CSVReaderException {
        String userInput = "Delicious" + "\n" // name
                + "1" + "\n" // rating
                + "10" + "\n" // distance
                + "50" + "\n" // price
                + "\n"; // cuisine

        String expectedBestMatches = """
                Best matches:
                Name: Deliciousgenix, Customer Rating: 4, Distance: 1, Price: 10, Cuisine: Spanish
                Name: Deliciouszilla, Customer Rating: 4, Distance: 1, Price: 15, Cuisine: Chinese
                Name: Havana Delicious, Customer Rating: 3, Distance: 1, Price: 35, Cuisine: Korean
                Name: Bang Delicious, Customer Rating: 5, Distance: 2, Price: 15, Cuisine: Russian
                Name: Crisp Delicious, Customer Rating: 5, Distance: 2, Price: 45, Cuisine: Russian
                """;

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        search.readParametersAndCalculateMatches();

        System.setOut(new PrintStream(outContent));
        search.displayBestMatches();
        assertEquals(expectedBestMatches, outContent.toString());
    }
    @Test
    void searchRestaurantsWithCuisine_shouldReturnFiveExpectedBestMatches() throws CSVReaderException {
        String userInput = "Delicious" + "\n" // name
                + "1" + "\n" // rating
                + "10" + "\n" // distance
                + "50" + "\n" // price
                + "Chinese" + "\n"; // cuisine

        String expectedBestMatches = """
                Best matches:
                Name: Deliciouszilla, Customer Rating: 4, Distance: 1, Price: 15, Cuisine: Chinese
                Name: Gusto Delicious, Customer Rating: 5, Distance: 3, Price: 50, Cuisine: Chinese
                Name: Deliciousoryx, Customer Rating: 1, Distance: 5, Price: 25, Cuisine: Chinese
                """;

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        search.readParametersAndCalculateMatches();

        System.setOut(new PrintStream(outContent));
        search.displayBestMatches();
        assertEquals(expectedBestMatches, outContent.toString());
    }
    @Test
    void searchRestaurantsWithInvalidRating_shouldReturnError() throws CSVReaderException {
        String userInput = "Delicious" + "\n" // name
                + "6" + "\n" // rating
                + "10" + "\n" // distance
                + "50" + "\n" // price
                + "Chinese" + "\n"; // cuisine

        String expectedOutput = "Parameter error: Customer rating should be from 1 to 5.";

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        System.setOut(new PrintStream(outContent));
        search.readParametersAndCalculateMatches();

        assertTrue(outContent.toString().contains(expectedOutput));
    }
    @Test
    void searchRestaurantsWithInvalidDistance_shouldReturnError() throws CSVReaderException {
        String userInput = "Delicious" + "\n" // name
                + "5" + "\n" // rating
                + "20" + "\n" // distance
                + "50" + "\n" // price
                + "Chinese" + "\n"; // cuisine

        String expectedOutput = "Parameter error: Distance should be from 1 to 10.";

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        System.setOut(new PrintStream(outContent));
        search.readParametersAndCalculateMatches();

        assertTrue(outContent.toString().contains(expectedOutput));
    }
    @Test
    void searchRestaurantsWithInvalidPrice_shouldReturnError() throws CSVReaderException {
        String userInput = "Delicious" + "\n" // name
                + "5" + "\n" // rating
                + "10" + "\n" // distance
                + "5" + "\n" // price
                + "Chinese" + "\n"; // cuisine

        String expectedOutput = "Parameter error: Price should be from 10 to 50.";

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        System.setOut(new PrintStream(outContent));
        search.readParametersAndCalculateMatches();

        assertTrue(outContent.toString().contains(expectedOutput));
    }
    @Test
    void searchRestaurantsWithNoRetaurantName_shouldReturnFiveExpectedBestMatches() throws CSVReaderException {
        String userInput = "\n" // name
                + "1" + "\n" // rating
                + "10" + "\n" // distance
                + "50" + "\n" // price
                + "American"; // cuisine

        String expectedBestMatches = """
                Best matches:
                Name: Wish Chow, Customer Rating: 3, Distance: 1, Price: 40, Cuisine: American
                Name: Kitchenster, Customer Rating: 2, Distance: 1, Price: 10, Cuisine: American
                Name: Fresh Table, Customer Rating: 3, Distance: 2, Price: 30, Cuisine: American
                Name: Tableio, Customer Rating: 3, Distance: 3, Price: 40, Cuisine: American
                Name: Bazaar Chow, Customer Rating: 4, Distance: 4, Price: 40, Cuisine: American
                """;

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        search.readParametersAndCalculateMatches();

        System.setOut(new PrintStream(outContent));
        search.displayBestMatches();
        assertEquals(expectedBestMatches, outContent.toString());
    }
    @Test
    void searchRestaurantsWithNonIntegerRating_shouldReturnError() throws CSVReaderException {
        String userInput = "Delicious" + "\n" // name
                + "A" + "\n" // rating
                + "10" + "\n" // distance
                + "50" + "\n" // price
                + "Chinese" + "\n"; // cuisine

        String expectedOutput = "Parameter error: Please provide an integer value for this parameter.";

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        System.setOut(new PrintStream(outContent));
        search.readParametersAndCalculateMatches();

        assertTrue(outContent.toString().contains(expectedOutput));
    }
    @Test
    void searchRestaurantsWithInvalidRetaurantName_shouldReturnNoResults() throws CSVReaderException {
        String userInput = "I dont exist" + "\n" // name
                + "1" + "\n" // rating
                + "10" + "\n" // distance
                + "50" + "\n" // price
                + "American"; // cuisine

        String expectedBestMatches = "No match found.\n";

        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        search.readParametersAndCalculateMatches();

        System.setOut(new PrintStream(outContent));
        search.displayBestMatches();
        assertEquals(expectedBestMatches, outContent.toString());
    }
}