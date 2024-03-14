package com.assessment;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;
import com.assessment.model.Restaurant;
import com.assessment.repository.CuisineRepository;
import com.assessment.repository.RestaurantRepository;

import java.util.List;

public class BestMatchingRestaurant {
    public static void main(String[] args) throws CSVReaderException {
        CuisineRepository cuisineRepository = new CuisineRepository();
        RestaurantRepository restaurantRepository = new RestaurantRepository();

        List<Cuisine> allCuisines = cuisineRepository.getAllCuisines();
        allCuisines.stream().forEach(cuisine -> System.out.println(cuisine.toString()));

        List<Restaurant> allRestaurants = restaurantRepository.getAllRestaurants();
        allRestaurants.stream().forEach(restaurant -> System.out.println(restaurant.toString()));

    }
}