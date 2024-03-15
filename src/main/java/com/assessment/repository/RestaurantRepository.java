package com.assessment.repository;

import com.assessment.reader.CSVReader;
import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Restaurant;

import java.util.List;

public class RestaurantRepository extends CSVReader {
    private final List<Restaurant> restaurants;

    public RestaurantRepository() throws CSVReaderException {
        restaurants = readRestaurantsFromFile();
    }

    private List<Restaurant> readRestaurantsFromFile() throws CSVReaderException {
        List<List<String>> allLines = loadSourceFromPath("csv/restaurants.csv");
        List<List<String>> lines = getLines(allLines);

        return lines.stream().map(Restaurant::fromLine).toList();
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}