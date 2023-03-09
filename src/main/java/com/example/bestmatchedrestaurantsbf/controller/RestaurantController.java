package com.example.bestmatchedrestaurantsbf.controller;

import com.example.bestmatchedrestaurantsbf.dto.Restaurant;
import com.example.bestmatchedrestaurantsbf.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@Slf4j
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    /**
     * Request that best matches restaurants based on criteria
     * @param restaurant (optional - can have name, customerRating, distance, price, cuisineId)
     * @return List of Restaurants that were matched to the user's criteria
     */
    @GetMapping(value="/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam(required = false) Restaurant restaurant) {
        log.info("GET Restaurants: " + restaurant);
        List<Restaurant> restaurants = restaurantService.matchBestRestaurants(restaurant);

        return ResponseEntity.ok(restaurants);
    }
}
