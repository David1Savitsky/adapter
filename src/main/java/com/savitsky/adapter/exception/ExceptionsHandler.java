package com.savitsky.adapter.exception;

import com.savitsky.adapter.dto.AdapterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(OpenWeatherMapException.class)
    public ResponseEntity<AdapterResponse<String>> handleOpenWeatherMapException(OpenWeatherMapException e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(AdapterResponse.error(e.getMessage()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<AdapterResponse<List<Object>>> handleMethodArgumentNotValid(
            HandlerMethodValidationException e) {
        final List<Object> violations = Arrays.stream(Objects.requireNonNull(e.getDetailMessageArguments())).toList();
        return ResponseEntity.status(BAD_REQUEST).body(AdapterResponse.error(violations));
    }
}
