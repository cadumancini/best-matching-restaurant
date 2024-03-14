package com.assessment.dataReader;

import com.assessment.exceptions.CSVReaderException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CSVReader implements RecordReader {
    private final String DELIMITER = ",";

    @Override
    public List<List<String>> loadSourceFromPath(String path) throws CSVReaderException {
        List<List<String>> records = new ArrayList<>();
        InputStream inputStream = getInputStreamFromPath(path);
        String line;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            while((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new CSVReaderException("An error occurred when reading the file: " + e.getMessage());
        }
        return records;
    }

    private InputStream getInputStreamFromPath(String path) throws CSVReaderException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        if (inputStream == null)
            throw new CSVReaderException(path + " is not found.");
        return inputStream;
    }

    @Override
    public List<List<String>> getLines(List<List<String>> source) {
        return source.stream().filter(line -> source.indexOf(line) > 0).collect(Collectors.toList()); // ignoring columns line
    }
}
