package com.example.microservice.customer_service.controller;

import com.example.microservice.customer_service.dto.CustomerRequest;
import com.example.microservice.customer_service.dto.CustomerResponse;
import com.example.microservice.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RefreshScope
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping("/getById/{id}")
    public CustomerResponse getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public CustomerResponse updateCustomer(@PathVariable Integer id, @RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(id, customerRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/getAll")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
