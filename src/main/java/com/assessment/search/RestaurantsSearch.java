package com.assessment.search;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.service.CuisineService;
import com.assessment.service.RestaurantService;

import java.util.Scanner;

public class RestaurantsSearch {
    private final CuisineService cuisineService;
    private final RestaurantService restaurantService;

    private String restaurantName;
    private int customerRating;
    private int distance;
    private int price;
    private String cuisine;

    public RestaurantsSearch() throws CSVReaderException {
        cuisineService = new CuisineService();
        restaurantService = new RestaurantService();
    }

    public void readParameters() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Best Matching Restaurants application!");
        System.out.println("Instructions: if you don't want the seach to use some of the parameters, just leave them blank and hit Enter.");

        System.out.println("Restaurant name:");
        restaurantName = scanner.nextLine();

        System.out.println("Customer rating:");
        customerRating = scanner.nextInt();

        System.out.println("Printing:");
        System.out.println(restaurantName);
        System.out.println(customerRating);
    }
}
