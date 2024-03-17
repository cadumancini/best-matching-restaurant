package com.assessment.search;

import com.assessment.model.Cuisine;
import com.assessment.model.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CriteriaMatcherTest {
    private final List<Restaurant> restaurants = Arrays.asList(
            new Restaurant("Deliciouspad",3,10,40,13),
            new Restaurant("Deliciousbea",5,6,50,15),
            new Restaurant("Gusto Delicious",5,3,50,2),
            new Restaurant("Traditional Chow",5,2,15,11),
            new Restaurant("Minty Chow",4,8,35,5),
            new Restaurant("Deliciousio",5,9,40,19),
            new Restaurant("Hearty ChowClick",2,6,25,8),
            new Restaurant("Fodder Table",4,1,20,8),
            new Restaurant("Perfection Grill",3,3,50,7)
    );
    @Test
    void filterRestaurantsByName_returnsExpectedRestaurants() {
        String name = "Delicious";
        List<Restaurant> restaurantsMatches = CriteriaMatcher.filterRestaurantsByName(name, restaurants);
        assertEquals(4, restaurantsMatches.size());
    }

    @Test
    void filterRestaurantsByName_returnsNoRestaurants_forInvalidName() {
        String name = "INVALID";
        List<Restaurant> restaurantsMatches = CriteriaMatcher.filterRestaurantsByName(name, restaurants);
        assertTrue(restaurantsMatches.isEmpty());
    }

    @Test
    void filterRestaurantsByRating_returnsExpectedRestaurants() {
        int rating = 3;
        List<Restaurant> restaurantsMatches = CriteriaMatcher.filterRestaurantsByRating(rating, restaurants);
        assertEquals(8, restaurantsMatches.size());
    }

    @Test
    void filterRestaurantsByDistance_returnsExpectedRestaurants() {
        int distance = 4;
        List<Restaurant> restaurantsMatches = CriteriaMatcher.filterRestaurantsByDistance(distance, restaurants);
        assertEquals(4, restaurantsMatches.size());
    }

    @Test
    void filterRestaurantsByPrice_returnsExpectedRestaurants() {
        int price = 30;
        List<Restaurant> restaurantsMatches = CriteriaMatcher.filterRestaurantsByPrice(price, restaurants);
        assertEquals(3, restaurantsMatches.size());
    }

    @Test
    void filterRestaurantsByCuisine_returnsExpectedRestaurants() {
        List<Cuisine> cuisines = Arrays.asList(
                new Cuisine(2, "Cuisine X"),
                new Cuisine(5, "Cuisine Y"),
                new Cuisine(8, "Cuisine Z"),
                new Cuisine(15, "The Best Cuisine")
        );
        List<Restaurant> restaurantsMatches = CriteriaMatcher.filterRestaurantsByCuisine(cuisines, restaurants);
        assertEquals(5, restaurantsMatches.size());
    }
}
