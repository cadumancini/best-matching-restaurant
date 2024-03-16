package com.assessment.service;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Restaurant;
import com.assessment.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService {
    private final RestaurantRepository repository;
    private static final String RESTAURANTS_CSV_FILE = "csv/restaurants.csv";

    public RestaurantService() throws CSVReaderException {
        repository = new RestaurantRepository(RESTAURANTS_CSV_FILE);
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.getRestaurants();
    }
}
