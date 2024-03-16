package com.assessment.search;

import com.assessment.exceptions.CSVReaderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantsSearchIntegrationTest {
    RestaurantsSearch search;

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;

    @BeforeEach
    void setup() throws CSVReaderException {
        search = new RestaurantsSearch();
    }

    @Test
    void searchByNameOnly_shouldReturnFiveBestMatches() throws CSVReaderException {
        String userInput = "Delicious" + "\n" // name
                + "0" + "\n" // rating
                + "0" + "\n" // distance
                + "0" + "\n" // price
                + "\n"; // cuisine

        String expectedBestMatches = "Best matches:\n" +
                "Name: Deliciousgenix, Customer Rating: 4, Distance: 1, Price: 10, Cuisine: Spanish\n" +
                "Name: Deliciouszilla, Customer Rating: 4, Distance: 1, Price: 15, Cuisine: Chinese\n" +
                "Name: Deliciouszoid, Customer Rating: 3, Distance: 2, Price: 30, Cuisine: Italian\n" +
                "Name: Deliciousquipo, Customer Rating: 2, Distance: 2, Price: 10, Cuisine: Other\n" +
                "Name: Deliciousish, Customer Rating: 1, Distance: 3, Price: 50, Cuisine: Greek\n";


        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        search = new RestaurantsSearch();
        search.readParameters();
        search.calculateMatches();

        System.setOut(new PrintStream(outContent));
        search.displayBestMatches();
        assertEquals(expectedBestMatches, outContent.toString());

        System.setIn(savedStandardInputStream);
        System.setOut(originalOut);
    }

}