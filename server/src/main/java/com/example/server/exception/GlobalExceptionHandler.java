package com.example.server.exception;

import com.example.server.dto.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(
            ResponseStatusException e
    ) {

        int status = e.getStatusCode().value();
        String message = e.getReason();

        ErrorResponse errorResponse =
                new ErrorResponse(status, message);

        return ResponseEntity
                .status(e.getStatusCode())
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e
    ) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        400,
                        "송금액이 바르지 않습니다"
                );

        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
}