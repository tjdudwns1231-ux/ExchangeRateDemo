package com.example.server.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api") //이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타내며, "/api" 경로로 들어오는 요청을 처리한다.
@CrossOrigin(origins = "http://localhost:5173") //이 컨트롤러가 다른 도메인(예: 프론트엔드 애플리케이션)에서 오는 요청을 허용하도록 설정한다. 여기서는 "http://localhost:5173"에서 오는 요청을 허용한다.
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService; //Controller가 사용할 Service를 저장하는 필드

    public ExchangeRateController(ExchangeRateService exchangeRateService) { //생성자 주입을 통해 ExchangeRateService를 주입받는다. 스프링부트는 생성자를 통해 필요한 의존성을 자동으로 주입한다. 이를 통해 TestController는 ExchangeRateService의 기능을 사용할 수 있다.
        this.exchangeRateService = exchangeRateService; //Spring이 생성자에 전달해준 Service를 내 Controller의 필드에 저장한다.
    }


    @PostMapping("/exchange") //를 붙이면 이 메서드가 POST 요청을 처리하는 엔드포인트임을 나타낸다. "/api/exchange" 경로로 들어오는 POST 요청을 이 메서드가 처리한다.
    public ExchangeCalculationResponse calculate( //요청 본문에 담긴 데이터를 ExchangeRequest 객체로 매핑한다.
        @RequestBody ExchangeRequest request) { //클라이언트가 보내는 JSON 데이터를 ExchangeRequest 객체로 변환하여 메서드의 매개변수로 전달한다.
        return exchangeRateService.calculateExchange(request); //Service의 calculateExchange 메서드를 호출하여 환율 계산을 수행하고, 그 결과를 반환한다. 반환된 ExchangeCalculationResponse 객체는 스프링부트에 의해 JSON 형식으로 변환되어 클라이언트에게 응답으로 전송된다.
    }


    @GetMapping("/exchange-rates/{currency}") //를 붙이면 이 메서드가 GET 요청을 처리하는 엔드포인트임을 나타낸다. "/api/exchange-rates/{currency}" 경로로 들어오는 GET 요청을 이 메서드가 처리한다. {currency}는 경로 변수로, 클라이언트가 요청할 때 특정 통화 코드를 지정할 수 있다.
    public ExchangeResponse getRate( //요청 경로에서 {currency} 부분을 추출하여 메서드의 매개변수로 전달한다.
        @PathVariable String currency) { //@PathVariable 어노테이션을 사용하여 경로 변수와 메서드 매개변수를 연결한다.
        return exchangeRateService.getRate(currency); //Service의 getRate 메서드를 호출하여 해당 통화의 환율 정보를 가져오고, 그 결과를 반환한다. 반환된 ExchangeResponse 객체는 스프링부트에 의해 JSON 형식으로 변환되어 클라이언트에게 응답으로 전송된다.
    }
}
