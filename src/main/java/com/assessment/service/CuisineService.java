package com.assessment.service;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;
import com.assessment.repository.CuisineRepository;

import java.util.List;

public class CuisineService {
    private final CuisineRepository repository;

    public CuisineService() throws CSVReaderException {
        repository = new CuisineRepository();
    }

    public List<Cuisine> getAllCuisines() {
        return repository.getCuisines();
    }

    public Cuisine getCuisine(int id) {
        return repository.getCuisineById(id);
    }
}