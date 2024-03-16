package com.assessment.repository;

import com.assessment.reader.CSVReader;
import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;

import java.util.List;

public class CuisineRepository extends CSVReader {
    private final List<Cuisine> cuisines;

    public CuisineRepository(String source) throws CSVReaderException {
        this.source = source;
        cuisines = readCuisinesFromFile();
    }

    private List<Cuisine> readCuisinesFromFile() throws CSVReaderException {
        List<List<String>> allLines = loadSourceFromPath(source);
        List<List<String>> lines = getLines(allLines);

        return lines.stream().map(Cuisine::fromLine).toList();
    }

    public List<Cuisine> getCuisinesByStartingName(String name) {
        return cuisines.stream().filter(cuisine -> cuisine.getName().startsWith(name)).toList();
    }

    public Cuisine getCuisineById(int cuisineId) {
        return cuisines.stream().filter(cuisine -> cuisine.getId() == cuisineId).findFirst().orElse(null);
    }
}