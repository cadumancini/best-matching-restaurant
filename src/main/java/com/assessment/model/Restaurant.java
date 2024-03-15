package com.assessment.model;

import java.util.List;

public class Restaurant {
    private final String name;
    private final int customeRating;
    private final int distance;
    private final int price;
    private final int cuisineId;

    public Restaurant(String name, int customeRating, int distance, int price, int cuisineId) {
        this.name = name;
        this.customeRating = customeRating;
        this.distance = distance;
        this.price = price;
        this.cuisineId = cuisineId;
    }

    public String getName() {
        return name;
    }

    public int getCustomeRating() {
        return customeRating;
    }

    public int getDistance() {
        return distance;
    }

    public int getPrice() {
        return price;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public static Restaurant fromLine(List<String> line) {
        return new Restaurant(line.get(0),
                Integer.parseInt(line.get(1)),
                Integer.parseInt(line.get(2)),
                Integer.parseInt(line.get(3)),
                Integer.parseInt(line.get(4)));
    }
}
