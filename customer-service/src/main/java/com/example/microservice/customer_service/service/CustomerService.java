package com.example.microservice.customer_service.service;

import com.example.microservice.customer_service.dto.AddressResponse;
import com.example.microservice.customer_service.dto.CustomerRequest;
import com.example.microservice.customer_service.dto.CustomerResponse;
import com.example.microservice.customer_service.entity.Customer;
import com.example.microservice.customer_service.exception.CustomerNotFoundException;
import com.example.microservice.customer_service.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressServiceClient addressServiceClient;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customer.setId(null);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerResponse.class);
    }

    public CustomerResponse getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

        AddressResponse addresses = addressServiceClient.getAddressById(customer.getAddressId());
        CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
        customerResponse.setAddress(addresses);
        return customerResponse;
    }

    public CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        modelMapper.map(customerRequest, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerResponse.class);
    }

    public void deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }
}
