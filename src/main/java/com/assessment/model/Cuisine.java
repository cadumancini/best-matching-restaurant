package com.assessment.model;

import java.util.List;

public class Cuisine {
    private final int id;
    private final String name;

    public Cuisine(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public static Cuisine fromLine(List<String> line) {
        return new Cuisine(Integer.valueOf(line.get(0)), line.get(1));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "id=" + id + ", name=" + name;
    }
}
