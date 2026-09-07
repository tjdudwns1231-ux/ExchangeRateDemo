package com.example.server.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.dto.ExchangeRequest;
import com.example.server.service.ExchangeRateService;
@RestController //를 붙이면 이 클래스가 REST API를 처리하는 컨트롤러임을 나타낸다. 스프링부트는 이 클래스를 스캔하여 Bean으로 등록한다. 스프링이 생성하고 관리하는 객체를 Bean이라고 한다. Bean은 스프링이 관리하는 객체를 의미하며, 스프링 컨테이너가 생성하고 관리한다. Bean은 싱글톤으로 관리되며, 애플리케이션 전역에서 공유된다.
public class TestController {

    private final ExchangeRateService exchangeRateService; //Controller가 사용할 Service를 저장하는 필드

    public TestController(ExchangeRateService exchangeRateService) { //생성자 주입을 통해 ExchangeRateService를 주입받는다. 스프링부트는 생성자를 통해 필요한 의존성을 자동으로 주입한다. 이를 통해 TestController는 ExchangeRateService의 기능을 사용할 수 있다.
        this.exchangeRateService = exchangeRateService; //Spring이 생성자에 전달해준 Service를 내 Controller의 필드에 저장한다.
    }

    @GetMapping("/api/test") //를 붙이면 이 메서드가 GET 요청을 처리하는 엔드포인트임을 나타낸다. "/api/test" 경로로 들어오는 GET 요청을 이 메서드가 처리한다.
    public String test() {
        return exchangeRateService.getTestMessage();
    }

    @PostMapping("/api/exchange")
    public String calculate(@RequestBody ExchangeRequest request) {
        return request.getCurrency() + " " + request.getAmount();
    }
}
