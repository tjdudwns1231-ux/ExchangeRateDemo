package com.example.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.example.server.api.CurrencyLayerApi;
import com.example.server.dto.CurrencyLayerResponse;
import com.example.server.dto.ExchangeCalculationResponse;
import com.example.server.dto.ExchangeRequest;
import com.example.server.service.ExchangeRateService;

@ExtendWith(MockitoExtension.class) // Mockito를 사용하기 위해 JUnit 5 확장 기능을 적용
public class ExchangeRateServiceTest {

    @Mock
    private CurrencyLayerApi currencyLayerApi; // CurrencyLayerApi를 모킹하여 실제 API 호출을 하지 않고 테스트 가능

    @InjectMocks
    private ExchangeRateService exchangeRateService; // ExchangeRateService에 모킹된 CurrencyLayerApi를 주입하여 테스트

    @Test
    void calculateExchange_success() {

        CurrencyLayerResponse response =
                new CurrencyLayerResponse();

        Map<String, BigDecimal> quotes = new HashMap<>();

        quotes.put("USDKRW", new BigDecimal("1300"));
        quotes.put("USDJPY", new BigDecimal("150"));
        quotes.put("USDPHP", new BigDecimal("60"));

        response.setSuccess(true);
        response.setSource("USD");
        response.setQuotes(quotes);

        when(currencyLayerApi.getRates())
                .thenReturn(response);

        ExchangeRequest request = new ExchangeRequest();
        request.setCurrency("KRW");
        request.setAmount(new BigDecimal("100"));

        ExchangeCalculationResponse result =
                exchangeRateService.calculateExchange(request);

        assertThat(result.getCurrency())
                .isEqualTo("KRW");

        assertThat(result.getRate())
                .isEqualByComparingTo("1300.00");

        assertThat(result.getAmount())
                .isEqualByComparingTo("100");

        assertThat(result.getReceivedAmount())
                .isEqualByComparingTo("130000.00");
    }
    @Test
    void calculateExchange_negativeAmount_throwsException() {
        ExchangeRequest request = new ExchangeRequest();
        request.setCurrency("KRW");
        request.setAmount(new BigDecimal("-1"));

        assertThatThrownBy(
                () -> exchangeRateService.calculateExchange(request)
        )
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("송금액이 바르지 않습니다");
    }
    @Test
    void calculateExchange_overMaximumAmount_throwsException() {
        ExchangeRequest request = new ExchangeRequest();
        request.setCurrency("KRW");
        request.setAmount(new BigDecimal("10001"));

        assertThatThrownBy(
                () -> exchangeRateService.calculateExchange(request)
        )
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("송금액이 바르지 않습니다");
    }
    @Test
    void calculateExchange_invalidCurrency_throwsException() {
        ExchangeRequest request = new ExchangeRequest();
        request.setCurrency("EUR");
        request.setAmount(new BigDecimal("100"));

        assertThatThrownBy(
                () -> exchangeRateService.calculateExchange(request)
        )
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Invalid currency");
    }
}