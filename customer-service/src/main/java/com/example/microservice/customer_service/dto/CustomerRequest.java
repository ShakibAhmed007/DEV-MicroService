package com.example.microservice.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private Short storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Short addressId;
    private Boolean activeBool;
    private Integer active;
}