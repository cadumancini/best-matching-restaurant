package com.assessment.repository;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryTest {
    private static final String VALID_CSV = "csv/restaurants.csv";
    private static final String NON_EXISTING_CSV = "csv/non_existing.csv";
    private static final String EMPTY_CSV = "csv/restaurantsEmpty.csv";

    RestaurantRepository repository;

    @Test
    void loadValidSource_returnsContent() throws CSVReaderException {
        repository = new RestaurantRepository(VALID_CSV);
        List<List<String>> csvContent = repository.loadSourceFromPath(VALID_CSV);
        assertFalse(csvContent.isEmpty());
    }

    @Test
    void loadNonExistingSource_throwsException() {
        assertThrows(CSVReaderException.class, () -> new RestaurantRepository(NON_EXISTING_CSV));
    }

    @Test
    void loadEmptySource_returnsEmptyList() throws CSVReaderException {
        repository = new RestaurantRepository(EMPTY_CSV);
        List<List<String>> csvContent = repository.loadSourceFromPath(EMPTY_CSV);
        assertTrue(csvContent.isEmpty());
    }

    @Test
    void getRestaurants_returnsListWithObjects_whenFileHasEntries() throws CSVReaderException {
        repository = new RestaurantRepository(VALID_CSV);
        List<Restaurant> restaurants = repository.getRestaurants();
        assertFalse(restaurants.isEmpty());
    }

    @Test
    void getRestaurants_returnsEmptyList_whenFileHasNoEntries() throws CSVReaderException {
        repository = new RestaurantRepository(EMPTY_CSV);
        List<Restaurant> restaurants = repository.getRestaurants();
        assertTrue(restaurants.isEmpty());
    }

    @Test
    void getRestaurant_returnsExpectedEntrieFromList() throws CSVReaderException {
        repository = new RestaurantRepository(VALID_CSV);
        List<Restaurant> restaurants = repository.getRestaurants();
        Restaurant expectedRestaurant = new Restaurant("Deliciousgenix", 4, 1, 10, 11);
        Restaurant actualRestaurant = restaurants.get(0);

        assertEquals(expectedRestaurant.getName(), actualRestaurant.getName());
        assertEquals(expectedRestaurant.getCustomeRating(), actualRestaurant.getCustomeRating());
        assertEquals(expectedRestaurant.getDistance(), actualRestaurant.getDistance());
        assertEquals(expectedRestaurant.getPrice(), actualRestaurant.getPrice());
        assertEquals(expectedRestaurant.getCuisineId(), actualRestaurant.getCuisineId());
    }
}