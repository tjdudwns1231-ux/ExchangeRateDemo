package com.example.server.exception;

import com.example.server.dto.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice //전역 예외 처리기
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class) //ResponseStatusException 예외 처리
    public ResponseEntity<ErrorResponse> handleResponseStatusException( //responseEntitiy는 HTTP status code와 body를 같이 설정해서 반환하는 객체
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