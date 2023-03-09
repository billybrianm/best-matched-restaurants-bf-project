package com.example.bestmatchedrestaurantsbf.controller;

import com.example.bestmatchedrestaurantsbf.dto.Restaurant;
import com.example.bestmatchedrestaurantsbf.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
@Slf4j
public class RestaurantController {
    RestaurantService restaurantService;

    @GetMapping(value="/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam(required = false) Restaurant restaurant) {
        log.info("GET Restaurants: " + restaurant);
        List<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(Restaurant.builder()
                        .name("Deliciousgenix")
                        .customerRating(4)
                        .distance(1)
                        .price(10)
                        .cuisineId(11)
                .build());

        return ResponseEntity.ok(restaurants);
    }
}
