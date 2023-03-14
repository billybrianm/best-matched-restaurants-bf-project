package com.example.bestmatchedrestaurantsbf.service;


import com.example.bestmatchedrestaurantsbf.config.FileProperties;
import com.example.bestmatchedrestaurantsbf.dto.Cuisine;
import com.example.bestmatchedrestaurantsbf.exception.InvalidCsvFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CuisineServiceTest {
    @InjectMocks
    @Spy
    CuisineService cuisineService;

    @Mock
    FileProperties fileProperties;

    List<Cuisine> cuisines = new ArrayList<>();
    @Before
    public void setup() {
        Cuisine cuisine1 = Cuisine.builder()
                .id(0)
                .name("test")
                .build();
        cuisines.add(cuisine1);

        Cuisine cuisine2 = Cuisine.builder()
                .id(1)
                .name("test1")
                .build();
        cuisines.add(cuisine2);
    }
    @Test
    public void getAllCuisines_Successful() {
        Mockito.when(cuisineService.loadCuisinesFromFile("data/cuisines.csv")).thenReturn(cuisines);
        Mockito.when(fileProperties.getCuisines()).thenReturn("data/cuisines.csv");

        List<Cuisine> cuisines = cuisineService.getAllCuisines();

        Assert.assertEquals(2, cuisines.size());
    }

    @Test
    public void loadCuisinesFromFile_Successful() {
        Mockito.when(fileProperties.getCuisines()).thenReturn("data/cuisines.csv");
        List<Cuisine> cuisineList = cuisineService.loadCuisinesFromFile(fileProperties.getCuisines());

        Assert.assertEquals(19, cuisineList.size());
        Assert.assertFalse(cuisineList.isEmpty());
    }

    @Test
    public void loadCuisinesFromFile_Error_InvalidCsvFileException() {
        Assertions.assertThrows(InvalidCsvFileException.class, () -> cuisineService.loadCuisinesFromFile("test"));
    }
}
