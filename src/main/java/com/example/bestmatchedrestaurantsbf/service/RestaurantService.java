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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantService {
    FileProperties fileProperties;

    /**
     * Main method from the service which will call the other methods to perform the logic of matching best restaurants
     * @param restaurantQuery
     * @return List<Restaurant>
     */
    public List<Restaurant> matchBestRestaurants(Restaurant restaurantQuery) {
        List<Restaurant> matchingRestaurants = loadRestaurantsFromFile(fileProperties.getRestaurants());

        if(restaurantQuery.getName() != null) {
            matchingRestaurants = getAllWithSimilarName(matchingRestaurants, restaurantQuery.getName().get());
        }
        if(restaurantQuery.getDistance() != null) {
            matchingRestaurants = getAllWithSimilarOrLowerDistance(matchingRestaurants, restaurantQuery.getDistance().get());
        }
        if(restaurantQuery.getCustomerRating() != null) {
            matchingRestaurants = getAllWithSimilarOrHigherRating(matchingRestaurants, restaurantQuery.getCustomerRating().get());
        }
        if(restaurantQuery.getPrice() != null) {
            matchingRestaurants = getAllWithSimilarOrLowerPrice(matchingRestaurants, restaurantQuery.getPrice().get());
        }
        if(restaurantQuery.getCuisineId() != null) {
            matchingRestaurants = getAllWithSameCuisine(matchingRestaurants, restaurantQuery.getCuisineId().get());
        }
        // Sort list to correct priorities after best matches are found
        matchingRestaurants = sortListByCriteria(matchingRestaurants);


        return matchingRestaurants.stream().limit(5).collect(Collectors.toList());
    }

    /**
     * Loads all restaurants from a CSV file configured in the application's configuration.
     * @return list of all Restaurants present on the CSV file
     */
    public List<Restaurant> loadRestaurantsFromFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(path).getFile()));
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

    /**
     * Filter list by names
     */
    public List<Restaurant> getAllWithSimilarName(List<Restaurant> list, String name){
        return list.stream().filter(rest -> rest.getName().get().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    /**
     * Filter list by rating (equals or higher)
     */
    public List<Restaurant> getAllWithSimilarOrHigherRating(List<Restaurant> list, Integer rating){
        return list.stream().filter(rest -> rest.getCustomerRating().get() >= rating).collect(Collectors.toList());
    }

    /**
     * Filter list by price (equals or lower)
     */
    public List<Restaurant> getAllWithSimilarOrLowerPrice(List<Restaurant> list, Integer price){
        return list.stream().filter(rest -> rest.getPrice().get() <= price).collect(Collectors.toList());
    }

    /**
     * Filter list by price (equals or lower)
     */
    public List<Restaurant> getAllWithSimilarOrLowerDistance(List<Restaurant> list, Integer distance){
        return list.stream().filter(rest -> rest.getDistance().get() <= distance).collect(Collectors.toList());
    }

    /**
     * Filter list by cuisine id
     */
    public List<Restaurant> getAllWithSameCuisine(List<Restaurant> list, Integer cuisine){
        return list.stream().filter(rest -> rest.getCuisineId().get().equals(cuisine)).collect(Collectors.toList());
    }

    /**
     * Sort list by lower price, then customer rating, then distance, thus sorting by best match possible to query
     */
    public List<Restaurant> sortListByCriteria(List<Restaurant> list) {
        Collections.sort(list, Comparator.comparingInt((Restaurant a) -> a.getPrice().get()));
        Collections.sort(list, Comparator.comparingInt((Restaurant a) -> a.getCustomerRating().get()).reversed());
        Collections.sort(list, Comparator.comparingInt((Restaurant a) -> a.getDistance().get()));
        return list;
    }
}
