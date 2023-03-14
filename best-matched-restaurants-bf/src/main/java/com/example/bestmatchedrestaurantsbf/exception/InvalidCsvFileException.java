package com.example.bestmatchedrestaurantsbf.exception;

import com.example.bestmatchedrestaurantsbf.constants.ERROR;

public class InvalidCsvFileException extends ErrorMessage {

    public InvalidCsvFileException(ERROR error) {
        super(error.getTitle(), error.getMessage(), error.getCode());
    }
}
