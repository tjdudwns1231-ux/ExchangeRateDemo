package com.example.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig { //RestTemplateConfig 클래스는 스프링부트에서 RestTemplate을 빈으로 등록하기 위한 설정 클래스이다.

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}