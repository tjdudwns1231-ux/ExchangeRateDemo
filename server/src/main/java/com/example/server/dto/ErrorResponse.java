package com.example.server.dto;

public class ErrorResponse { //이 클래스는 서버에서 발생한 오류를 나타내는 DTO(Data Transfer Object)입니다. 클라이언트에게 오류 정보를 전달하기 위해 사용됩니다.
                                //GlobalExceptionHandler에서 예외가 발생했을 때, 이 클래스의 인스턴스를 생성하여 클라이언트에게 반환합니다.
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}