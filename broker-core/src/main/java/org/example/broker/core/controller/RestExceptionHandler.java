package org.example.broker.core.controller;

import org.example.broker.core.dto.HttpErrorResponse;
import org.example.broker.core.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<HttpErrorResponse> handleValidationException(ValidationException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new HttpErrorResponse(exception.getErrors()));
    }

}
