package com.assessment.service;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.model.Cuisine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuisineServiceIntegrationTest {
    CuisineService service;

    @BeforeEach
    void setup() throws CSVReaderException {
        service = new CuisineService();
    }

    @Test
    void getCuisinesByStartingName_returnsListWithExactValue_whenOnlyOneExists() {
        List<Cuisine> cuisines = service.getCuisinesByStartingName("American");
        Cuisine expectedCuisine = new Cuisine(1, "American");

        assertEquals(1, cuisines.size());
        assertEquals(expectedCuisine.getId(), cuisines.get(0).getId());
        assertEquals(expectedCuisine.getName(), cuisines.get(0).getName());
    }
    @Test
    void getCuisinesByStartingName_returnsListWithMultipleValues_whenManyMatchesAreFound() {
        List<Cuisine> cuisines = service.getCuisinesByStartingName("I");

        assertEquals(3, cuisines.size());
        assertEquals("Italian", cuisines.get(0).getName());
        assertEquals("Indian", cuisines.get(1).getName());
        assertEquals("Indonesian", cuisines.get(2).getName());
    }
    @Test
    void getCuisinesByStartingName_returnsEmptyList_whenNoMatchIsFound() {
        List<Cuisine> cuisines = service.getCuisinesByStartingName("B");
        assertTrue(cuisines.isEmpty());
    }

    @Test
    void getCuisineById_returnsExpectedCuisine_whenCuisineExists() {
        Cuisine actualCuisine = service.getCuisineById(1);
        Cuisine expectedCuisine = new Cuisine(1, "American");

        assertEquals(expectedCuisine.getId(), actualCuisine.getId());
        assertEquals(expectedCuisine.getName(), actualCuisine.getName());
    }

    @Test
    void getCuisineById_returnsNull_whenCuisineDoesNotExist() {
        assertNull(service.getCuisineById(35));
    }
}