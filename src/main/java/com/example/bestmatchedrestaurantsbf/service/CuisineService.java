package com.example.bestmatchedrestaurantsbf.service;

import com.example.bestmatchedrestaurantsbf.config.FileProperties;
import com.example.bestmatchedrestaurantsbf.constants.ERROR;
import com.example.bestmatchedrestaurantsbf.dto.Cuisine;
import com.example.bestmatchedrestaurantsbf.exception.InvalidCsvFileException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CuisineService {
    FileProperties fileProperties;

    public List<Cuisine> getAllCuisines() {
        return loadCuisinesFromFile(fileProperties.getCuisines());
    }

    /**
     * Loads all cuisines from a CSV file configured in the application's configuration.
     * @return list of all Cuisines present on the CSV file
     */
    public List<Cuisine> loadCuisinesFromFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(path).getFile()));
            List<Cuisine> cuisineList = new ArrayList<>();
            String line = br.readLine(); // skips the first line which is the header
            while ((line = br.readLine()) != null) {
                String[] cuisineCsv = line.split(",");
                cuisineList.add(Cuisine.builder()
                        .id(Integer.valueOf(cuisineCsv[0]))
                        .name(cuisineCsv[1])
                        .build());
            }
            return cuisineList;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new InvalidCsvFileException(ERROR.INVALID_CSV_FILE);
        }
    }
}
