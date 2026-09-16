package com.example.server.api;
import com.example.server.dto.CurrencyLayerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CurrencyLayerApi { //CurrencyLayerApi 클래스는 외부 환율 API를 호출하여 환율 정보를 가져오는 역할을 담당한다. 
                                    //RestTemplate을 사용하여 HTTP 요청을 보내고, 응답을 CurrencyLayerResponse DTO로 매핑한다.
    private final RestTemplate restTemplate;

    @Value("${currencylayer.access-key}")
    private String accessKey; // application-secret.properties에서 가져온 access key (API key)

    public CurrencyLayerApi(RestTemplate restTemplate) { // 외부 api 호출을 위해 RestTemplate 주입
        this.restTemplate = restTemplate;
    }

    public CurrencyLayerResponse getRates() { //CurrencyLayerApi를 통해 실제 환율정보를 가져오는 메서드. 외부 API 호출을 수행하고, 응답을 CurrencyLayerResponse DTO로 반환한다.

        String url = // 외부 환율 API의 URL을 구성. access key와 요청할 통화 정보를 포함
                "https://api.currencylayer.com/live"
                + "?access_key=" + accessKey
                + "&source=USD"
                + "&currencies=KRW,JPY,PHP";

        try { // 외부 API 호출을 수행하고, 응답을 CurrencyLayerResponse DTO로 매핑
            CurrencyLayerResponse response = restTemplate.getForObject( //RestTemplate의 getForObject 메서드를 사용하여 외부 API 호출. URL과 응답 타입을 지정
                    url,
                    CurrencyLayerResponse.class // 응답을 CurrencyLayerResponse DTO로 매핑
            );

            if (response == null // 응답이 null이거나, success가 false인 경우 예외 발생
                    || !Boolean.TRUE.equals(response.getSuccess())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "환율 API 응답이 올바르지 않습니다."
                );
            }

            if (response.getQuotes() == null) { // 응답에 환율 정보가 없는 경우 예외 발생
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "환율 정보가 없습니다."
                );
            }

            return response;

        } catch (RestClientException e) { // 외부 API 호출 중 예외 발생 시, 예외 정보를 로그로 출력하고, BAD_GATEWAY 상태 코드와 함께 ResponseStatusException을 발생시킨다.

            System.out.println("=== CurrencyLayer API ERROR ==="); // 예외 발생 시 로그 출력
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());

            throw new ResponseStatusException( // BAD_GATEWAY 상태 코드와 함께 ResponseStatusException 발생
                    HttpStatus.BAD_GATEWAY,
                    "환율 API 호출에 실패했습니다."
            );
        }
    }
}
