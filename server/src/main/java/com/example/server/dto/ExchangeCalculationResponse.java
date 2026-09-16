package com.example.server.dto;

import java.math.BigDecimal;

public class ExchangeCalculationResponse { //환율 계산 결과를 나타내는 DTO(Data Transfer Object) 클래스. 클라이언트에게 환율 계산 결과 데이터를 전달하기 위해 사용된다.

    private String currency; //환율 계산에 사용된 통화 코드를 나타내는 필드정의. 예를 들어, "USD", "EUR" 등의 통화 코드가 될 수 있다.
    private BigDecimal rate; //환율을 나타내는 필드정의. BigDecimal 타입을 사용하여 소수점 이하의 정확한 계산이 가능하도록 한다.
    private BigDecimal amount; //환율 계산에 사용된 금액을 나타내는 필드정의. BigDecimal 타입을 사용하여 소수점 이하의 정확한 계산이 가능하도록 한다.
    private BigDecimal receivedAmount; //환율 계산 결과로 받은 금액을 나타내는 필드정의. BigDecimal 타입을 사용하여 소수점 이하의 정확한 계산이 가능하도록 한다.

    public ExchangeCalculationResponse( //service에서 계산한 결과값 4개를 전달받아 각 필드에 저장하는 생성자 정의
            String currency, 
            BigDecimal rate, 
            BigDecimal amount, 
            BigDecimal receivedAmount 
    ) {
        this.currency = currency; // service에서 계산한 currency 값을 필드에 저장
        this.rate = rate; // service에서 계산한 rate 값을 필드에 저장
        this.amount = amount;   // service에서 계산한 amount 값을 필드에 저장
        this.receivedAmount = receivedAmount; // service에서 계산한 receivedAmount 값을 필드에 저장
    }
    // 외부 코드에서 필드에 직접 접근하지 않고 Getter를 통해 읽게 함
    public String getCurrency() { // 이 객체에 저장되어 있는 currency 값을 꺼내서 반환
        return currency;
    }

    public BigDecimal getRate() { // 이 객체에 저장되어 있는 rate 값을 꺼내서 반환
        return rate;
    }

    public BigDecimal getAmount() { // 이 객체에 저장되어 있는 amount 값을 꺼내서 반환
        return amount;
    }

    public BigDecimal getReceivedAmount() { // 이 객체에 저장되어 있는 receivedAmount 값을 꺼내서 반환
        return receivedAmount;
    }
}