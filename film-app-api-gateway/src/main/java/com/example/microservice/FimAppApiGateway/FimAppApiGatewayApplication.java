package com.example.microservice.FimAppApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FimAppApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FimAppApiGatewayApplication.class, args);
	}

}
