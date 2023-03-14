package com.example.bestmatchedrestaurantsbf.exception;

import com.example.bestmatchedrestaurantsbf.constants.ERROR;
import com.example.bestmatchedrestaurantsbf.controller.RestaurantController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = { RestaurantController.class } )
@Slf4j
public class RestaurantRestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidCsvFileException.class)
    public ResponseEntity<InvalidCsvFileException> handleInvalidCsvFileException(InvalidCsvFileException ex, WebRequest request) {
        log.error(String.format("Exception %s on Request %s", ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI()));
        return new ResponseEntity<>(ex, HttpStatus.valueOf(ex.getCode()));
    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if(ex.getClass().equals(BindException.class)) {
            log.error(String.format("Exception %s on Request %s", ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI()));
            List<String> invalidParameters = ((BindException) ex).getBindingResult().getFieldErrors().stream().map((inv -> inv.getField())).collect(Collectors.toList());

            InvalidParamException invalidParamException = new InvalidParamException(ERROR.INVALID_PARAM_EXCEPTION, invalidParameters);
            return new ResponseEntity<>(invalidParamException, HttpStatus.valueOf(invalidParamException.getCode()));
        } else {
            return super.handleExceptionInternal(ex, body, headers, statusCode, request);
        }
    }
}
