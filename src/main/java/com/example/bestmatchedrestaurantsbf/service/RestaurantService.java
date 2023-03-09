package com.example.bestmatchedrestaurantsbf.service;

import com.example.bestmatchedrestaurantsbf.config.FileProperties;
import com.example.bestmatchedrestaurantsbf.constants.ERROR;
import com.example.bestmatchedrestaurantsbf.dto.Restaurant;
import com.example.bestmatchedrestaurantsbf.exception.InvalidCsvFileException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantService {
    FileProperties fileProperties;

    public List<Restaurant> matchBestRestaurants(Restaurant restaurant) {
        return loadRestaurantsFromFile();
    }

    /**
     * Loads all restaurants from a CSV file configures in the application's configuration.
     * @return list of all Restaurants present on the CSV file
     */
    public List<Restaurant> loadRestaurantsFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(fileProperties.getRestaurants()).getFile()));
            List<Restaurant> restaurantList = new ArrayList<>();
            String line = br.readLine(); // skips the first line which is the header
            while ((line = br.readLine()) != null) {
                String[] restaurantCsv = line.split(",");
                restaurantList.add(Restaurant.builder()
                        .name(restaurantCsv[0])
                        .customerRating(Integer.valueOf(restaurantCsv[1]))
                        .distance(Integer.valueOf(restaurantCsv[2]))
                        .price(Integer.valueOf(restaurantCsv[3]))
                        .cuisineId(Integer.valueOf(restaurantCsv[4]))
                        .build());
            }
            return restaurantList;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new InvalidCsvFileException(ERROR.INVALID_CSV_FILE);
        }
    }
}
