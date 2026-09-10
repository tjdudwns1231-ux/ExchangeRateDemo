package com.example.server.dto;

import java.math.BigDecimal;

public class ExchangeCalculationResponse {

    private String currency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal receivedAmount;

    public ExchangeCalculationResponse(
            String currency,
            BigDecimal rate,
            BigDecimal amount,
            BigDecimal receivedAmount
    ) {
        this.currency = currency;
        this.rate = rate;
        this.amount = amount;
        this.receivedAmount = receivedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }
}