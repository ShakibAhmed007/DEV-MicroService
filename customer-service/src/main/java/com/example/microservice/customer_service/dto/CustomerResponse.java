package com.example.microservice.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Integer id;
    private Short storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Short addressId;
    private Boolean activeBool;
    private OffsetDateTime createDate;
    private OffsetDateTime lastUpdate;
    private Integer active;
    private AddressResponse address;
}
