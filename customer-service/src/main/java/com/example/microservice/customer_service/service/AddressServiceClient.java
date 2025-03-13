package com.example.microservice.customer_service.service;

import com.example.microservice.customer_service.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="addressservice")
public interface AddressServiceClient {

    @GetMapping("/addresses/getById/{id}")
    public AddressResponse getAddressById(@PathVariable Integer id);
}
