package com.example.bestmatchedrestaurantsbf.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ERROR {

    INVALID_CSV_FILE("Invalid Csv File", "The default csv file configured is not accessible.", 500),
    INVALID_PARAM_EXCEPTION("Invalid parameters", "The following fields have invalid values: %s", 400);
    String title;
    String message;
    Integer code;
}
