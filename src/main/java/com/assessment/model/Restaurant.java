package com.assessment.model;

import java.util.List;

public class Restaurant {
    private final String name;
    private final int customer_rating;
    private final int distance;
    private final int price;
    private final int cuisine_id;

    public Restaurant(String name, int customer_rating, int distance, int price, int cuisine_id) {
        this.name = name;
        this.customer_rating = customer_rating;
        this.distance = distance;
        this.price = price;
        this.cuisine_id = cuisine_id;
    }

    public String getName() {
        return name;
    }

    public int getCustomer_rating() {
        return customer_rating;
    }

    public int getDistance() {
        return distance;
    }

    public int getPrice() {
        return price;
    }

    public int getCuisine_id() {
        return cuisine_id;
    }

    public static Restaurant fromLine(List<String> line) {
        return new Restaurant(line.get(0),
                Integer.valueOf(line.get(1)),
                Integer.valueOf(line.get(2)),
                Integer.valueOf(line.get(3)),
                Integer.valueOf(line.get(4)));
    }

    public String toString() {
        return "name=" + name + ", customer_rating=" + customer_rating + ", distance=" + distance + ", price=" + price + ", cuisine_id=" + cuisine_id;
    }
}
