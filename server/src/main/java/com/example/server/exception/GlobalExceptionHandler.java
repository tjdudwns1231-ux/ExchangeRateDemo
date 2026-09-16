package com.example.server.exception;

import com.example.server.dto.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice 
public class GlobalExceptionHandler { //GlobalExceptionHandler 클래스는 전역 예외 처리기 역할을 수행하며, 컨트롤러에서 발생한 예외를 처리하고 적절한 HTTP 응답을 반환한다.
                                        // @RestControllerAdvice 어노테이션을 사용하여 스프링부트가 이 클래스를 전역 예외 처리기로 인식하도록 한다.

    @ExceptionHandler(ResponseStatusException.class) //ResponseStatusException이 발생하면 아래 메서드가 처리한다.
    public ResponseEntity<ErrorResponse> handleResponseStatusException( //responseEntitiy는 HTTP status code와 body를 같이 설정해서 반환하는 객체
            ResponseStatusException e //ResponseStatusException 예외를 처리하는 메서드. 이 메서드는 ResponseStatusException이 발생했을 때 호출되며, 
                //HTTP 상태 코드와 메시지를 포함한 ErrorResponse 객체를 반환한다.          
    ) {

        int status = e.getStatusCode().value(); // 발생한 예외에서 HTTP 상태 코드와 오류 메세지를 꺼냄.
        String message = e.getReason(); 

        ErrorResponse errorResponse = //꺼낸 상태 코드와 메세지를 ErrorResponse dto에 담음.
                new ErrorResponse(status, message);

        return ResponseEntity //responseEntity를 사용하여 HTTP 상태 코드와 ErrorResponse dto를 함께 반환한다.
                .status(e.getStatusCode())
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class) //HttpMessageNotReadableException이 발생하면 아래 메서드가 처리한다. 이 예외는 클라이언트가 잘못된 JSON 형식의 요청을 보냈을 때 발생한다.
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException( //HttpMessageNotReadableException 예외를 처리하는 메서드. 이 메서드는 HttpMessageNotReadableException이 발생했을 때 호출되며, 
        //HTTP 상태 코드와 메시지를 포함한 ErrorResponse 객체를 반환한다.
            HttpMessageNotReadableException e
    ) {

        ErrorResponse errorResponse = //ErrorResponse 객체를 생성하여 HTTP 상태 코드와 메시지를 설정한다.
                new ErrorResponse(
                        400,
                        "송금액이 바르지 않습니다"
                );

        return ResponseEntity //responseEntity를 사용하여 HTTP 상태 코드와 ErrorResponse 객체를 함께 반환한다.
                .badRequest()
                .body(errorResponse);
    }
}