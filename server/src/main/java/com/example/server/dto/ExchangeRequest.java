package com.example.server.dto;
import java.math.BigDecimal;

public class ExchangeRequest { //환율 계산 요청을 나타내는 DTO(Data Transfer Object) 클래스. 클라이언트로(프론트엔드부터) 환율 계산 요청 데이터를 전달받기 위해 사용된다.

    private String currency; //환율 계산에 사용될 통화 코드를 나타내는 필드정의. 예를 들어, "USD", "EUR" 등의 통화 코드가 될 수 있다.
    private BigDecimal amount; //환율 계산에 사용될 금액을 나타내는 필드정의. BigDecimal 타입을 사용하여 소수점 이하의 정확한 계산이 가능하도록 한다.

    public String getCurrency() { //currency 필드의 값을 반환하는 getter 메서드. 클라이언트가 요청한 통화 코드를 가져올 때 사용된다.
        return currency;
    }

    public void setCurrency(String currency) { //currency 필드의 값을 설정하는 setter 메서드. 클라이언트가 요청한 통화 코드를 설정할 때 사용된다.
        this.currency = currency;
    }

    public BigDecimal getAmount() { //amount 필드의 값을 반환하는 getter 메서드. 클라이언트가 요청한 금액을 가져올 때 사용된다. 
        return amount;
    }

    public void setAmount(BigDecimal amount) { //amount 필드의 값을 설정하는 setter 메서드. 클라이언트가 요청한 금액을 설정할 때 사용된다.
        this.amount = amount;
    }
}