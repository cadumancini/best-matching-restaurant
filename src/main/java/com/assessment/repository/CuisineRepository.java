package com.assessment.repository;

import com.assessment.dataReader.CSVReader;
import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;

import java.util.List;
import java.util.stream.Collectors;

public class CuisineRepository extends CSVReader {
    public List<Cuisine> getAllCuisines() throws CSVReaderException {
        List<List<String>> allLines = loadSourceFromPath("csv/cuisines.csv");
        List<List<String>> lines = getLines(allLines);

        return lines.stream().map(line -> Cuisine.fromLine(line)).collect(Collectors.toList());
    }
}
