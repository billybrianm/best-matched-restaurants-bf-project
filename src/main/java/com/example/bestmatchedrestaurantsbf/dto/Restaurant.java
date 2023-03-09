package com.example.bestmatchedrestaurantsbf.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    String name;
    int customerRating;
    int distance;
    int price;
    int cuisineId;
}
