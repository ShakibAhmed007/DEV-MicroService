package com.example.addressservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AddressserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressserviceApplication.class, args);
	}

}
