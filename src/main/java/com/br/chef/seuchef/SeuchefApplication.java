package com.br.chef.seuchef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SeuchefApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeuchefApplication.class, args);
	}
}
