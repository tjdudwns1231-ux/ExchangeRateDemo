package com.example.server.service;

import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.server.api.CurrencyLayerApi;
import com.example.server.dto.CurrencyLayerResponse;
import com.example.server.dto.ExchangeResponse;
import com.example.server.dto.ExchangeCalculationResponse;
import com.example.server.dto.ExchangeRequest;
import java.math.RoundingMode;

@Service
public class ExchangeRateService {

    private final CurrencyLayerApi currencyLayerApi; //Service가 사용할 CurrencyLayerApi를 저장하는 필드

    public ExchangeRateService(CurrencyLayerApi currencyLayerApi) { //생성자 주입을 통해 CurrencyLayerApi를 주입받는다. 스프링부트는 생성자를 통해 필요한 의존성을 자동으로 주입한다. 이를 통해 ExchangeRateService는 CurrencyLayerApi의 기능을 사용할 수 있다.
        this.currencyLayerApi = currencyLayerApi;
    }

    public ExchangeResponse getRate(String currency) { //특정 통화의 환율정보를 가져오는 메서드
       
        validateCurrency(currency);

        BigDecimal rate = findRate(currency); //findRate 메서드를 통해 실제 환율정보를 가져와서 BigDecimal rate에 담음

        return new ExchangeResponse(currency, rate); //가져온 환율정보를 ExchangeResponse dto에 담아서 반환
    }

    public ExchangeCalculationResponse calculateExchange(ExchangeRequest request) { //송금액 계산메서드
        
        String currency = request.getCurrency(); //exchangeRequest dto에서 통화와 송금액을 가져옴
        BigDecimal amount = request.getAmount();

        validateCurrency(currency); // 그 다음 통화검증, 송금액 검증 메서드 호출
        validateAmount(amount);

        BigDecimal rate = findRate(currency); //findRate 메서드를 통해 실제 환율정보를 가져와서 BigDecimal rate에 담음

        BigDecimal receivedAmount =
                amount.multiply(rate)
                      .setScale(2, RoundingMode.HALF_UP); // 송금액에 환율을 곱해서 수취금액을 계산하고 소수점 둘째자리까지 반올림

        BigDecimal displayRate =
                rate.setScale(2, RoundingMode.HALF_UP); // 환율도 소수점 둘째자리까지 반올림

        return new ExchangeCalculationResponse( // 계산된 결과를 ExchangeCalculationResponse dto에 담아서 반환
                currency,
                displayRate,
                amount,
                receivedAmount
        );
    }

    private void validateCurrency(String currency) { //통화 검증 / 만약 krw, jpy, php가 아니면 예외 발생하고 bad request 반환
        if (!"KRW".equals(currency)
                && !"JPY".equals(currency)
                && !"PHP".equals(currency)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid currency: " + currency
            );
        }
    }

    private void validateAmount(BigDecimal amount) { //송금액 검증 / 만약 amount가 null이거나 0보다 작거나 10000보다 크면 예외 발생하고 bad request 반환
        if (amount == null
                || amount.compareTo(BigDecimal.ZERO) < 0
                || amount.compareTo(new BigDecimal("10000")) > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "송금액이 바르지 않습니다"
            );
        }
    }
    private BigDecimal findRate(String currency) {

        CurrencyLayerResponse response = currencyLayerApi.getRates(); //CurrencyLayerApi를 통해 실제 환율정보를 가져와서 currencyLayerResponse dto에 담음

        String key = "USD" + currency; // USD와 통화를 합쳐서 key를 만듦 (예: USDKRW, USDJPY, USDPHP)

        BigDecimal rate = response.getQuotes().get(key); // key를 통해 currencyLayerResponse dto에서 해당 통화의 환율을 가져옴

        if (rate == null) { // 만약 rate가 null이면 예외 발생하고 bad gateway 반환
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "요청한 환율 정보를 찾을 수 없습니다."
            );
        }

        return rate;
    }
}