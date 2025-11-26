package com.pragma.powerup.foodcourtmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.pragma.powerup.foodcourtmicroservice.infrastructure.output.feign.client")
public class FoodCourtMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCourtMicroserviceApplication.class, args);
	}

}
