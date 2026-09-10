package com.example.server.dto;

import java.math.BigDecimal;

public class ExchangeResponse {

    private String currency;
    private BigDecimal rate;

    public ExchangeResponse(String currency, BigDecimal rate) {
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
