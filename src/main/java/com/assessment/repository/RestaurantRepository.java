package com.assessment.repository;

import com.assessment.dataReader.CSVReader;
import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantRepository extends CSVReader {
    public List<Restaurant> getAllRestaurants() throws CSVReaderException {
        List<List<String>> allLines = loadSourceFromPath("csv/restaurants.csv");
        List<List<String>> lines = getLines(allLines);

        return lines.stream().map(line -> Restaurant.fromLine(line)).collect(Collectors.toList());
    }
}