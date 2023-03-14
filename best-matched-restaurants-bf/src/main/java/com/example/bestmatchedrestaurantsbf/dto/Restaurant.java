package com.example.bestmatchedrestaurantsbf.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    Optional<String> name;
    Optional<Integer> customerRating;
    Optional<Integer> distance;
    Optional<Integer> price;
    Optional<Integer> cuisineId;
}
