package com.example.server.service;
import org.springframework.stereotype.Service;

@Service //를 붙이면 이 클래스가 서비스 계층임을 나타낸다. 스프링부트는 이 클래스를 스캔하여 Bean으로 등록한다. 스프링이 생성하고 관리하는 객체를 Bean이라고 한다. Bean은 스프링이 관리하는 객체를 의미하며, 스프링 컨테이너가 생성하고 관리한다. Bean은 싱글톤으로 관리되며, 애플리케이션 전역에서 공유된다.
public class ExchangeRateService {

    public String getTestMessage() {
        return "Service OK";
    }

}