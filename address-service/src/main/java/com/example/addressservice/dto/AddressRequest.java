package com.example.addressservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String address;
    private String address2;
    private String district;
    private Short cityId;
    private String postalCode;
    private String phone;
}
