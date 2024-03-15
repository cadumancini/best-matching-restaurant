package com.assessment.search;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Restaurant;
import com.assessment.service.CuisineService;
import com.assessment.service.RestaurantService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class RestaurantsSearch {
    private final CuisineService cuisineService;
    private final RestaurantService restaurantService;
    private final Scanner scanner;
    private String restaurantName;
    private int customerRating;
    private int distance;
    private int price;
    private String cuisine;

    private List<Restaurant> matchingRestaurants;
    private final int NUMBER_OF_MATCHES_WANTED = 5;

    public RestaurantsSearch() throws CSVReaderException {
        cuisineService = new CuisineService();
        restaurantService = new RestaurantService();
        scanner = new Scanner(System.in);
    }

    public void readParameters() {
        print("Welcome to the Best Matching Restaurants application!");
        print("Instructions: if you don't want the search to use some of the parameters, just leave them blank and hit Enter.");

        print("Restaurant name:");
        restaurantName = readInput();

        print("Customer rating:");
        customerRating = getIntFrom(readInput());

        print("Distance:");
        distance = getIntFrom(readInput());

        print("Price:");
        price = getIntFrom(readInput());

        print("Cuisine:");
        cuisine = readInput();
    }

    public void calculateMatches() {
        matchingRestaurants = new ArrayList<>(restaurantService.getAllRestaurants());

        if (!restaurantName.isEmpty()) matchingRestaurants = CriteriaMatcher.filterRestaurantsByStartingName(restaurantName, matchingRestaurants);
        if (customerRating > 0) matchingRestaurants = CriteriaMatcher.filterRestaurantsByRating(customerRating, matchingRestaurants);
        if (distance > 0) matchingRestaurants = CriteriaMatcher.filterRestaurantsByDistance(distance, matchingRestaurants);
        if (price > 0) matchingRestaurants = CriteriaMatcher.filterRestaurantsByPrice(price, matchingRestaurants);
        if(!cuisine.isEmpty()) matchingRestaurants = CriteriaMatcher.filterRestaurantsByCuisine(cuisineService.getCuisinesByStartingName(cuisine), matchingRestaurants);
    }

    public void displayBestMatches() {
        if (!matchingRestaurants.isEmpty()) {
            List<Restaurant> sortedMatches = sortAndTrim();
            print("Best matches:");
            sortedMatches.forEach(this::printRestaurant);
        }
        else print("No match found.");
    }

    private List<Restaurant> sortAndTrim() {
        List<Restaurant> sortedMatches = sortMatches(matchingRestaurants);
        if (sortedMatches.size() > NUMBER_OF_MATCHES_WANTED) {
            sortedMatches.retainAll(sortedMatches.stream().filter(restaurant -> sortedMatches.indexOf(restaurant) < NUMBER_OF_MATCHES_WANTED).toList());
        }
        return sortedMatches;
    }

    private List<Restaurant> sortMatches(List<Restaurant> matchingRestaurants) {
        List<Restaurant> sortedRestaurants = new ArrayList<>(matchingRestaurants);
        sortedRestaurants.sort(Comparator.comparing(Restaurant::getDistance)
                .thenComparing(Restaurant::getCustomer_rating, Comparator.reverseOrder())
                .thenComparing(Restaurant::getPrice)
                .thenComparing(Restaurant::getName));
        return sortedRestaurants;
    }

    private void print(String message) {
        System.out.println(message);
    }

    private void printRestaurant(Restaurant restaurant) {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(restaurant.getName());
        builder.append(", Customer Rating: ").append(restaurant.getCustomer_rating());
        builder.append(", Distance: ").append(restaurant.getDistance());
        builder.append(", Price: ").append(restaurant.getPrice());
        builder.append(", Cuisine: ").append(cuisineService.getCuisineById(restaurant.getCuisine_id()).getName());
        print(builder.toString());
    }

    private String readInput() {
        return scanner.nextLine();
    }

    private int getIntFrom(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
