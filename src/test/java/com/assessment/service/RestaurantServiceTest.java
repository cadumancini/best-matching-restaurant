package com.assessment.service;

import com.assessment.model.Restaurant;
import com.assessment.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantServiceTest {
    RestaurantRepository mockRepository = Mockito.mock(RestaurantRepository.class);
    RestaurantService service = new RestaurantService(mockRepository);

    @Test
    void getAllRestaurants_returnsListOfValues() {
        when(mockRepository.getRestaurants()).thenReturn(Arrays.asList(
                new Restaurant("Restaurant A", 5, 1, 10, 1),
                new Restaurant("Restaurant B", 4, 2, 20, 2),
                new Restaurant("Restaurant C", 3, 3, 30, 3),
                new Restaurant("Restaurant D", 2, 4, 40, 4)
        ));
        List<Restaurant> allRestaurants = service.getAllRestaurants();
        assertFalse(allRestaurants.isEmpty());
        assertEquals(4, allRestaurants.size());
    }

    @Test
    void getAllRestaurants_returnsEmptyList_whenRepositoryFoundsNothing() {
        when(mockRepository.getRestaurants()).thenReturn(new ArrayList<>());
        List<Restaurant> allRestaurants = service.getAllRestaurants();
        assertTrue(allRestaurants.isEmpty());
    }

}