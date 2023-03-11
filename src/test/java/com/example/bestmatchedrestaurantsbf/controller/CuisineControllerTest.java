package com.example.bestmatchedrestaurantsbf.controller;

import com.example.bestmatchedrestaurantsbf.dto.Cuisine;
import com.example.bestmatchedrestaurantsbf.service.CuisineService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CuisineControllerTest {
    @InjectMocks
    CuisineController cuisineController;

    @Mock
    CuisineService cuisineService;

    @Test
    public void getCuisines_Successful() {
        ResponseEntity<List<Cuisine>> response = cuisineController.getCuisines();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
