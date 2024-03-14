package com.assessment.service;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Restaurant;
import com.assessment.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService() throws CSVReaderException {
        repository = new RestaurantRepository();
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.getRestaurants();
    }
}
