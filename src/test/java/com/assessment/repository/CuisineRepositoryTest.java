package com.assessment.repository;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuisineRepositoryTest {
    private static final String VALID_CSV = "csv/cuisines.csv";
    private static final String NON_EXISTING_CSV = "csv/non_existing.csv";
    private static final String EMPTY_CSV = "csv/cuisinesEmpty.csv";
    CuisineRepository repository;

    @Test
    void loadValidSource_returnsContent() throws CSVReaderException {
        repository = new CuisineRepository(VALID_CSV);
        List<List<String>> csvContent = repository.loadSourceFromPath(VALID_CSV);
        assertFalse(csvContent.isEmpty());
    }

    @Test
    void loadNonExistingSource_throwsException() {
        assertThrows(CSVReaderException.class, () -> new CuisineRepository(NON_EXISTING_CSV));
    }

    @Test
    void loadEmptySource_returnsEmptyList() throws CSVReaderException {
        repository = new CuisineRepository(EMPTY_CSV);
        List<List<String>> csvContent = repository.loadSourceFromPath(EMPTY_CSV);
        assertTrue(csvContent.isEmpty());
    }

    @Test
    void getCuisineById_returnsExpectedCuisine_whenCuisineExists() throws CSVReaderException {
        repository = new CuisineRepository(VALID_CSV);
        Cuisine expectedCuisine = new Cuisine(1, "American");
        Cuisine actualCuisine = repository.getCuisineById(1);

        assertEquals(expectedCuisine.getId(), actualCuisine.getId());
        assertEquals(expectedCuisine.getName(), actualCuisine.getName());
    }

    @Test
    void getCuisineById_returnsNull_whenCuisineDoesNotExist() throws CSVReaderException {
        repository = new CuisineRepository(VALID_CSV);
        assertNull(repository.getCuisineById(35));
    }

    @Test
    void getCuisinesByStartingName_returnsExpectedCuisines() throws CSVReaderException {
        repository = new CuisineRepository(VALID_CSV);
        List<Cuisine> cuisines = repository.getCuisinesByStartingName("Delicious");

        assertEquals(0, cuisines.size());
    }

    @Test
    void getCuisinesByStartingName_returnsEmptyList_whenNoCuisineIsFound() throws CSVReaderException {
        repository = new CuisineRepository(VALID_CSV);
        List<Cuisine> cuisines = repository.getCuisinesByStartingName("NonExistingCuisine");

        assertTrue(cuisines.isEmpty());
    }
}