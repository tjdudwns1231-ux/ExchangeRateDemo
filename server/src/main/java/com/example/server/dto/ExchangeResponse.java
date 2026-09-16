package com.example.server.dto;

import java.math.BigDecimal;

public class ExchangeResponse { //환율 정보를 나타내는 DTO(Data Transfer Object) 클래스. 클라이언트에게 환율 정보를 전달하기 위해 사용된다.

    private String currency; //환율 정보를 나타내는 통화 코드를 나타내는 필드정의. 예를 들어, "USD", "EUR" 등의 통화 코드가 될 수 있다.
    private BigDecimal rate; //환율 정보를 나타내는 환율 값을 나타내는 필드정의. BigDecimal 타입을 사용하여 소수점 이하의 정확한 계산이 가능하도록 한다.

    public ExchangeResponse(String currency, BigDecimal rate) { //생성자를 통해 ExchangeResponse 객체를 생성할 때 통화 코드와 환율 값을 설정한다.
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
