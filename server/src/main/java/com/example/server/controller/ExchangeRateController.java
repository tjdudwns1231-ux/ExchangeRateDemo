package com.example.server.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.server.dto.ExchangeCalculationResponse;
import com.example.server.dto.ExchangeRequest;
import com.example.server.service.ExchangeRateService;
import com.example.server.dto.ExchangeResponse;
@RestController
@RequestMapping("/api")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService; //Controller가 사용할 Service를 저장하는 필드

    public ExchangeRateController(ExchangeRateService exchangeRateService) { //생성자 주입을 통해 ExchangeRateService를 주입받는다. 스프링부트는 생성자를 통해 필요한 의존성을 자동으로 주입한다. 이를 통해 TestController는 ExchangeRateService의 기능을 사용할 수 있다.
        this.exchangeRateService = exchangeRateService; //Spring이 생성자에 전달해준 Service를 내 Controller의 필드에 저장한다.
    }


    @PostMapping("/exchange") //를 붙이면 이 메서드가 POST 요청을 처리하는 엔드포인트임을 나타낸다. "/api/exchange" 경로로 들어오는 POST 요청을 이 메서드가 처리한다.
    public ExchangeCalculationResponse calculate(
        @RequestBody ExchangeRequest request) {
        return exchangeRateService.calculateExchange(request);
    }


    @GetMapping("/exchange-rates/{currency}")
    public ExchangeResponse getRate(
        @PathVariable String currency) {
        return exchangeRateService.getRate(currency);
    }
}
