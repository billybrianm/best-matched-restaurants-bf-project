package com.example.bestmatchedrestaurantsbf.controller;

import com.example.bestmatchedrestaurantsbf.dto.Cuisine;
import com.example.bestmatchedrestaurantsbf.service.CuisineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // TODO: use spring security to deal with CORS
@RequestMapping("")
@Slf4j
public class CuisineController {

    @Autowired
    CuisineService cuisineService;
    /**
     * Request that will list all cuisines
     * @return List of all Cuisines
     */
    @GetMapping(value="/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cuisine>> getCuisines() {
        log.info("Received request to list cuisines");
        List<Cuisine> cuisines = cuisineService.getAllCuisines();

        log.info("All cuisines listed with OK status.");
        return ResponseEntity.ok(cuisines);
    }
}
