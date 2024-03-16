package com.assessment.service;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceIntegrationTest {
    RestaurantService service;

    @BeforeEach
    void setup() throws CSVReaderException {
        service = new RestaurantService();
    }

    @Test
    void getAllRestaurants_returnsListWithAllRestaurants() {
        List<Restaurant> allRestaurants = service.getAllRestaurants();
        assertFalse(allRestaurants.isEmpty());

        Restaurant expectedRestaurant = new Restaurant("Deliciousgenix", 4, 1, 10, 11);
        Restaurant actualRestaurant = allRestaurants.get(0);

        assertEquals(expectedRestaurant.getName(), actualRestaurant.getName());
        assertEquals(expectedRestaurant.getCustomeRating(), actualRestaurant.getCustomeRating());
        assertEquals(expectedRestaurant.getDistance(), actualRestaurant.getDistance());
        assertEquals(expectedRestaurant.getPrice(), actualRestaurant.getPrice());
        assertEquals(expectedRestaurant.getCuisineId(), actualRestaurant.getCuisineId());
    }
}