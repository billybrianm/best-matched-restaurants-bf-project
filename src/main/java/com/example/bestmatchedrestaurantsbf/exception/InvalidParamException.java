package com.example.bestmatchedrestaurantsbf.exception;

import com.example.bestmatchedrestaurantsbf.constants.ERROR;

import java.util.List;

public class InvalidParamException extends ErrorMessage {

    public InvalidParamException(ERROR error, List<String> invalidFields) {
        super(error.getTitle(), String.format(error.getMessage(), invalidFields), error.getCode());
    }
}
