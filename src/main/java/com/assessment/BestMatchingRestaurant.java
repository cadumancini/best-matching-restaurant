package com.assessment;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;
import com.assessment.model.Restaurant;
import com.assessment.service.CuisineService;
import com.assessment.service.RestaurantService;

import java.util.List;

public class BestMatchingRestaurant {
    public static void main(String[] args) throws CSVReaderException {
        CuisineService cuisineService = new CuisineService();
        RestaurantService restaurantService = new RestaurantService();

        List<Cuisine> allCuisines = cuisineService.getAllCuisines();
        allCuisines.stream().forEach(cuisine -> System.out.println(cuisine.toString()));

        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants();
        allRestaurants.stream().forEach(restaurant -> System.out.println(restaurant.toString()));

    }
}
