package com.assessment.service;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;
import com.assessment.repository.CuisineRepository;

import java.util.List;

public class CuisineService {
    private final CuisineRepository repository;
    private static final String CUISINES_CSV_FILE = "csv/cuisines.csv";

    public CuisineService() throws CSVReaderException {
        repository = new CuisineRepository(CUISINES_CSV_FILE);
    }

    public List<Cuisine> getCuisinesByStartingName(String name) {
        return repository.getCuisinesByStartingName(name);
    }

    public Cuisine getCuisineById(int cuisineId) {
        return repository.getCuisineById(cuisineId);
    }
}