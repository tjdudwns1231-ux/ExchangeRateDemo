package com.example.server.dto;

public class CurrencyLayerError { //이 클래스는 CurrencyLayer API에서 발생한 오류를 나타내는 DTO(Data Transfer Object)입니다.

    private Integer code;
    private String info;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}