package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //를 붙이면 스프링부트가 자동으로 설정된다. 현재 패키지와 하위 패키지를 스캔하여 controller, service, repository 등을 찾아서 Bean으로 등록한다.
public class ExchangeRateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateDemoApplication.class, args); //이 코드가 실제로 스프링부트 애플리케이션을 실행한다. 내부적으로 톰캣 서버를 실행하고, 스프링 컨테이너를 초기화한다.
	}

}
