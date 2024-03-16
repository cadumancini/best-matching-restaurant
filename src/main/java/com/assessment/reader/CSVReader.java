package com.assessment.reader;

import com.assessment.exceptions.CSVReaderException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CSVReader implements RecordReader {
    private static final String DELIMITER = ",";
    protected String source;

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
        return source.stream().filter(line -> source.indexOf(line) >= 1).toList(); // ignoring columns line
    }
}
