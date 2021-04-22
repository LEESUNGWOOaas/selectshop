package com.example.selectshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 시간 변경이 자동으로 가능 
@SpringBootApplication //스프링부트임을 선언
public class SelectshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelectshopApplication.class, args);
    }

}
