package com.example.bestmatchedrestaurantsbf.controller;

import com.example.bestmatchedrestaurantsbf.dto.Restaurant;
import com.example.bestmatchedrestaurantsbf.service.RestaurantService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {
    @InjectMocks
    RestaurantController restaurantController;
    @Mock
    RestaurantService restaurantService;

    @Test
    public void getRestaurants_Successful() {

        Restaurant query = Restaurant.builder().name(Optional.of("alpha")).build();
        ResponseEntity<List<Restaurant>> response = restaurantController.getRestaurants(query);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
