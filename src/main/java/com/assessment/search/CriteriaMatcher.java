package com.assessment.search;

import com.assessment.model.Cuisine;
import com.assessment.model.Restaurant;

import java.util.List;

public class CriteriaMatcher {
    private CriteriaMatcher(){}
    public static List<Restaurant> filterRestaurantsByStartingName(String name, List<Restaurant> restaurants) {
        return restaurants.stream().filter(restaurant -> restaurant.getName().startsWith(name)).toList();
    }

    public static List<Restaurant> filterRestaurantsByRating(int customerRating, List<Restaurant> restaurants) {
        return restaurants.stream().filter(restaurant -> restaurant.getCustomeRating() >= customerRating).toList();
    }

    public static List<Restaurant> filterRestaurantsByDistance(int distance, List<Restaurant> restaurants) {
        return restaurants.stream().filter(restaurant -> restaurant.getDistance() <= distance).toList();
    }

    public static List<Restaurant> filterRestaurantsByPrice(int price, List<Restaurant> restaurants) {
        return restaurants.stream().filter(restaurant -> restaurant.getPrice() <= price).toList();
    }

    public static List<Restaurant> filterRestaurantsByCuisine(List<Cuisine> cuisines, List<Restaurant> restaurants) {
        return restaurants.stream().filter(restaurant -> cuisines.stream().anyMatch(cuisine -> cuisine.getId() == restaurant.getCuisineId())).toList();
    }
}
