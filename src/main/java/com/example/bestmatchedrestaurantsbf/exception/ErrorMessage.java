package com.example.bestmatchedrestaurantsbf.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"stackTrace", "suppressedExceptions", "suppressed", "localizedMessage", "cause", "detailMessage"})
public class ErrorMessage extends RuntimeException {
    String title;
    String message;
    Integer code;
}
