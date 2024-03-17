package com.assessment;

import com.assessment.exceptions.CSVReaderException;
import com.assessment.search.RestaurantsSearch;

public class BestMatchingRestaurant {
    public static void main(String[] args) throws CSVReaderException {
        RestaurantsSearch search = new RestaurantsSearch();
        search.readParametersAndCalculateMatches();
        search.displayBestMatches();
    }
}
