package com.example.addressservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Integer addressId;
    private String address;
    private String address2;
    private String district;
    private Short cityId;
    private String postalCode;
    private String phone;
    private OffsetDateTime lastUpdate;
}
