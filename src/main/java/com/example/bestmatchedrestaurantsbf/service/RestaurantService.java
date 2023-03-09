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
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantService {
    FileProperties fileProperties;

    /**
     * Main method from the service which will call the other methods to perform the logic of matching best restaurants
     * @param restaurant
     * @return List<Restaurant>
     */
    public List<Restaurant> matchBestRestaurants(Restaurant restaurant) {
        List<Restaurant> restaurantList = loadRestaurantsFromFile();


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
                        .name(Optional.of(restaurantCsv[0]))
                        .customerRating(Optional.of(Integer.valueOf(restaurantCsv[1])))
                        .distance(Optional.of(Integer.valueOf(restaurantCsv[2])))
                        .price(Optional.of(Integer.valueOf(restaurantCsv[3])))
                        .cuisineId(Optional.of(Integer.valueOf(restaurantCsv[4])))
                        .build());
            }
            return restaurantList;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new InvalidCsvFileException(ERROR.INVALID_CSV_FILE);
        }
    }
}
