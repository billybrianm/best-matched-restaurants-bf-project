package com.example.bestmatchedrestaurantsbf.service;

import com.example.bestmatchedrestaurantsbf.config.FileProperties;
import com.example.bestmatchedrestaurantsbf.dto.Restaurant;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantServiceTest {
    @InjectMocks
    @Spy
    RestaurantService restaurantService;

    @Mock
    FileProperties fileProperties;

    List<Restaurant> restaurants = new ArrayList<>();

    Restaurant restaurant1, restaurant2,restaurant3, restaurant4;
    @Before
    public void setup() {
        restaurant1 = Restaurant.builder()
                .name(Optional.of("alpha"))
                .price(Optional.of(10))
                .customerRating(Optional.of(4))
                .distance(Optional.of(18))
                .cuisineId(Optional.of(3))
                .build();
        restaurants.add(restaurant1);

        restaurant2 = Restaurant.builder()
                .name(Optional.of("beta"))
                .price(Optional.of(10))
                .customerRating(Optional.of(5))
                .distance(Optional.of(20))
                .cuisineId(Optional.of(3))
                .build();
        restaurants.add(restaurant2);

        restaurant3 = Restaurant.builder()
                .name(Optional.of("charlie"))
                .price(Optional.of(5))
                .customerRating(Optional.of(3))
                .distance(Optional.of(4))
                .cuisineId(Optional.of(11))
                .build();
        restaurants.add(restaurant3);

        restaurant4 = Restaurant.builder()
                .name(Optional.of("delta"))
                .price(Optional.of(5))
                .customerRating(Optional.of(3))
                .distance(Optional.of(1))
                .cuisineId(Optional.of(11))
                .build();
        restaurants.add(restaurant4);
    }

    @Test
    public void matchBestRestaurants_Name_Successful() {
        Mockito.when(restaurantService.loadRestaurantsFromFile("data/restaurants.csv")).thenReturn(restaurants);
        Mockito.when(fileProperties.getRestaurants()).thenReturn("data/restaurants.csv");

        Restaurant restaurant = Restaurant.builder().name(Optional.of("alpha")).build();
        List<Restaurant> restaurantList = restaurantService.matchBestRestaurants(restaurant);

        Assert.assertEquals(restaurantList.get(0), restaurant1);
    }

    @Test
    public void matchBestRestaurants_Distance_Successful() {
        Mockito.when(restaurantService.loadRestaurantsFromFile("data/restaurants.csv")).thenReturn(restaurants);
        Mockito.when(fileProperties.getRestaurants()).thenReturn("data/restaurants.csv");

        Restaurant restaurant = Restaurant.builder().distance(Optional.of(4)).build();
        List<Restaurant> restaurantList = restaurantService.matchBestRestaurants(restaurant);

        Assert.assertTrue(restaurantList.contains(restaurant3));
        Assert.assertTrue(restaurantList.contains(restaurant4));
    }

    @Test
    public void matchBestRestaurants_CustomerRating_Successful() {
        Mockito.when(restaurantService.loadRestaurantsFromFile("data/restaurants.csv")).thenReturn(restaurants);
        Mockito.when(fileProperties.getRestaurants()).thenReturn("data/restaurants.csv");

        Restaurant restaurant = Restaurant.builder().customerRating(Optional.of(5)).build();
        List<Restaurant> restaurantList = restaurantService.matchBestRestaurants(restaurant);

        Assert.assertEquals(1, restaurantList.size());
        Assert.assertTrue(restaurantList.contains(restaurant2));
    }

    @Test
    public void matchBestRestaurants_Price_Successful() {
        Mockito.when(restaurantService.loadRestaurantsFromFile("data/restaurants.csv")).thenReturn(restaurants);
        Mockito.when(fileProperties.getRestaurants()).thenReturn("data/restaurants.csv");

        Restaurant restaurant = Restaurant.builder().price(Optional.of(5)).build();
        List<Restaurant> restaurantList = restaurantService.matchBestRestaurants(restaurant);

        Assert.assertEquals(2, restaurantList.size());
        Assert.assertTrue(restaurantList.contains(restaurant3));
        Assert.assertTrue(restaurantList.contains(restaurant4));
    }

    @Test
    public void matchBestRestaurants_CuisineId_Successful() {
        Mockito.when(restaurantService.loadRestaurantsFromFile("data/restaurants.csv")).thenReturn(restaurants);
        Mockito.when(fileProperties.getRestaurants()).thenReturn("data/restaurants.csv");

        Restaurant restaurant = Restaurant.builder().cuisineId(Optional.of(3)).build();
        List<Restaurant> restaurantList = restaurantService.matchBestRestaurants(restaurant);

        Assert.assertEquals(2, restaurantList.size());
        Assert.assertTrue(restaurantList.contains(restaurant1));
        Assert.assertTrue(restaurantList.contains(restaurant2));
    }

    @Test
    public void matchBestRestaurants_All_Successful() {
        Mockito.when(restaurantService.loadRestaurantsFromFile("data/restaurants.csv")).thenReturn(restaurants);
        Mockito.when(fileProperties.getRestaurants()).thenReturn("data/restaurants.csv");

        Restaurant restaurant = Restaurant.builder()
                .name(Optional.of("alpha"))
                .price(Optional.of(10))
                .customerRating(Optional.of(4))
                .distance(Optional.of(18))
                .cuisineId(Optional.of(3))
                .build();

        List<Restaurant> restaurantList = restaurantService.matchBestRestaurants(restaurant);

        Assert.assertEquals(1, restaurantList.size());
        Assert.assertTrue(restaurantList.contains(restaurant1));
    }

    @Test
    public void loadRestaurantsFromFile_Successful() {
        Mockito.when(fileProperties.getRestaurants()).thenReturn("data/restaurants.csv");
        List<Restaurant> restaurantList = restaurantService.loadRestaurantsFromFile(fileProperties.getRestaurants());

        Assert.assertFalse(restaurantList.isEmpty());
    }

    @Test
    public void loadRestaurantsFromFile_Error_InvalidCsvFileException() {
        Assertions.assertThrows(InvalidCsvFileException.class, () -> restaurantService.loadRestaurantsFromFile("test"));
    }
    @Test
    public void getAllWithSimilarName_Successful() {
        List<Restaurant> restaurantList = restaurantService.getAllWithSimilarName(restaurants, "alpha");

        Assert.assertEquals(restaurant1, restaurantList.get(0));
    }

    @Test
    public void getAllWithSimilarOrHigherRating_Successful() {
        List<Restaurant> restaurantList = restaurantService.getAllWithSimilarOrHigherRating(restaurants, 5);

        Assert.assertEquals(restaurant2, restaurantList.get(0));
    }

    @Test
    public void getAllWithSimilarOrLowerPrice_Successful() {
        List<Restaurant> restaurantList = restaurantService.getAllWithSimilarOrLowerPrice(restaurants, 5);

        Assert.assertEquals(restaurant3, restaurantList.get(0));
    }

    @Test
    public void getAllWithSimilarOrLowerDistance_Successful() {
        List<Restaurant> restaurantList = restaurantService.getAllWithSimilarOrLowerDistance(restaurants, 2);

        Assert.assertEquals(restaurant4, restaurantList.get(0));
    }

    @Test
    public void getAllWithSameCuisine_Successful() {
        List<Restaurant> restaurantList = restaurantService.getAllWithSameCuisine(restaurants, 3);

        Assert.assertTrue(restaurantList.contains(restaurant1));
        Assert.assertTrue(restaurantList.contains(restaurant2));
    }

    @Test
    public void sortListByCriteria_Distance_Successful() {
        List<Restaurant> restaurantList = restaurantService.sortListByCriteria(restaurants);

        Assert.assertEquals(restaurant4, restaurantList.get(0));
    }

    @Test
    public void sortListByCriteria_CustomerRating_Successful() {
        // have restaurant2 match distance with restaurant4 so that the tiebreaker is the customerRating
        restaurants.get(1).setDistance(Optional.of(1));

        List<Restaurant> restaurantList = restaurantService.sortListByCriteria(restaurants);

        Assert.assertEquals(restaurant2, restaurantList.get(0));
    }
}
