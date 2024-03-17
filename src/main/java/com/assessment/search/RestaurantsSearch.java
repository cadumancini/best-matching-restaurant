package com.assessment.search;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.exceptions.UserInputException;
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
    private final Scanner scanner = new Scanner(System.in);
    private List<Restaurant> matchingRestaurants;
    private static final int NUMBER_OF_MATCHES_WANTED = 5;

    public RestaurantsSearch() throws CSVReaderException {
        cuisineService = new CuisineService();
        restaurantService = new RestaurantService();
    }

    public void readParametersAndCalculateMatches() {
        try {
            print("Welcome to the Best Matching Restaurants application!");

            print("Restaurant name:");
            String name = readInput();

            print("Customer rating (1 - 5):");
            int customerRating = getCustomerRating();

            print("Distance (1 - 10):");
            int distance = getDistance();

            print("Price (10 - 50):");
            int price = getPrice();

            print("Cuisine:");
            String cuisine = readInput();

            calculateMatches(name, customerRating, distance, price, cuisine);
        } catch (UserInputException ex) {
            print("Parameter error: " + ex.getMessage());
        }
    }

    private int getCustomerRating() {
        int customerRating = getIntFrom(readInput());
        if (customerRating < 1 || customerRating > 5) throw new UserInputException("Customer rating should be from 1 to 5.");
        return customerRating;
    }

    private int getDistance() {
        int distance = getIntFrom(readInput());
        if (distance < 1 || distance > 10) throw new UserInputException("Distance should be from 1 to 10.");
        return distance;
    }

    private int getPrice() {
        int price = getIntFrom(readInput());
        if (price < 10 || price > 50) throw new UserInputException("Price should be from 10 to 50.");
        return price;
    }

    private void calculateMatches(String name, int rating, int distance, int price, String cuisine) {
        matchingRestaurants = new ArrayList<>(restaurantService.getAllRestaurants());
        if (!name.isEmpty()) matchingRestaurants = CriteriaMatcher.filterRestaurantsByName(name, matchingRestaurants);
        matchingRestaurants = CriteriaMatcher.filterRestaurantsByRating(rating, matchingRestaurants);
        matchingRestaurants = CriteriaMatcher.filterRestaurantsByDistance(distance, matchingRestaurants);
        matchingRestaurants = CriteriaMatcher.filterRestaurantsByPrice(price, matchingRestaurants);
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
                .thenComparing(Restaurant::getCustomeRating, Comparator.reverseOrder())
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
        builder.append(", Customer Rating: ").append(restaurant.getCustomeRating());
        builder.append(", Distance: ").append(restaurant.getDistance());
        builder.append(", Price: ").append(restaurant.getPrice());
        builder.append(", Cuisine: ").append(cuisineService.getCuisineById(restaurant.getCuisineId()).getName());
        print(builder.toString());
    }

    private String readInput() {
        return scanner.nextLine();
    }

    private int getIntFrom(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new UserInputException("Please provide an integer value for this parameter.");
        }
    }
}
