package com.example.bestmatchedrestaurantsbf.exception;

import com.example.bestmatchedrestaurantsbf.controller.RestaurantController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = { RestaurantController.class } )
@Slf4j
public class RestaurantRestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidCsvFileException.class)
    public ResponseEntity<InvalidCsvFileException> handleInvalidCsvFileException(InvalidCsvFileException ex, WebRequest request) {
        log.error(String.format("Exception %s on Request %s", ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI()));
        return new ResponseEntity<>(ex, HttpStatus.valueOf(ex.getCode()));
    }
}
