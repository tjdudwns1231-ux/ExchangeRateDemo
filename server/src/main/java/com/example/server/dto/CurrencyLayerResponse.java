package com.example.server.dto;
import java.math.BigDecimal;
import java.util.Map;

public class CurrencyLayerResponse {

    private String source;
    private Map<String, BigDecimal> quotes;
    private Boolean success;
    private CurrencyLayerError error;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, BigDecimal> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, BigDecimal> quotes) {
        this.quotes = quotes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CurrencyLayerError getError() {
        return error;
    }

    public void setError(CurrencyLayerError error) {
        this.error = error;
    }

}
