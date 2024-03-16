package com.assessment.service;

import com.assessment.model.Cuisine;
import com.assessment.repository.CuisineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CuisineServiceTest {
    CuisineRepository mockRepository = Mockito.mock(CuisineRepository.class);
    CuisineService service = new CuisineService(mockRepository);

    @BeforeEach
    void setupMocks() {
        when(mockRepository.getCuisineById(1)).thenReturn(new Cuisine(1, "MockedCuisine"));
        when(mockRepository.getCuisineById(35)).thenReturn(null);
        when(mockRepository.getCuisinesByStartingName("I")).thenReturn(Arrays.asList(
                new Cuisine(5, "Italian"),
                new Cuisine(10, "Irish")
        ));
        when(mockRepository.getCuisinesByStartingName("X")).thenReturn(new ArrayList<>());
    }

    @Test
    void getCuisineById_returnsMockedCuisine() {
        Cuisine expectedCuisine = new Cuisine(1, "MockedCuisine");

        Cuisine actualCuisine = service.getCuisineById(1);
        assertNotNull(actualCuisine);
        assertEquals(expectedCuisine.getId(), actualCuisine.getId());
        assertEquals(expectedCuisine.getName(), actualCuisine.getName());
    }

    @Test
    void getCuisineById_returnsNullMockedCuisine_forInvalidId() {
        assertNull(service.getCuisineById(35));
    }

    @Test
    void getCuisinesByStartingName_returnsListValues_whenManyMatchesAreFound() {
        List<Cuisine> cuisines = service.getCuisinesByStartingName("I");
        assertEquals(2, cuisines.size());
    }

    @Test
    void getCuisinesByStartingName_returnsEmptyList_whenNoMatchIsFound() {
        List<Cuisine> cuisines = service.getCuisinesByStartingName("X");
        assertTrue(cuisines.isEmpty());
    }
}