package com.example.microservice.customer_service.service;

import com.example.microservice.customer_service.dto.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="addressservice")
public interface AddressServiceClient {

    @GetMapping("/addresses/getById/{id}")
    @Retry(name="addressservice")
    @CircuitBreaker(name="addressservice", fallbackMethod = "getAddressByIdFallback")
    public AddressResponse getAddressById(@PathVariable Integer id);

    default AddressResponse getAddressByIdFallback(Integer id, Throwable exception) {
        System.out.println("Exception: " + exception.getMessage());
        System.out.println("Parameter: " + id);
        return new AddressResponse();
    }
}
