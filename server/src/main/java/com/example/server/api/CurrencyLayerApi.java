package com.example.server.api;
import com.example.server.dto.CurrencyLayerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CurrencyLayerApi {

    private final RestTemplate restTemplate;

    @Value("${currencylayer.access-key}")
    private String accessKey; // application-secret.properties에서 가져온 access key (API key)

    public CurrencyLayerApi(RestTemplate restTemplate) { // 외부 api 호출을 위해 RestTemplate 주입
        this.restTemplate = restTemplate;
    }

    public CurrencyLayerResponse getRates() {

        String url =
                "https://api.currencylayer.com/live"
                + "?access_key=" + accessKey
                + "&source=USD"
                + "&currencies=KRW,JPY,PHP";

        try {
            CurrencyLayerResponse response = restTemplate.getForObject(
                    url,
                    CurrencyLayerResponse.class
            );

            if (response == null
                    || !Boolean.TRUE.equals(response.getSuccess())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "환율 API 응답이 올바르지 않습니다."
                );
            }

            if (response.getQuotes() == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "환율 정보가 없습니다."
                );
            }

            return response;

        } catch (RestClientException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "환율 API 호출에 실패했습니다."
            );
        }
    }
}
