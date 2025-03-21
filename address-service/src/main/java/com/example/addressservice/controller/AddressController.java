package com.example.addressservice.controller;

import com.example.addressservice.dto.AddressRequest;
import com.example.addressservice.dto.AddressResponse;
import com.example.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RefreshScope
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse createAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.createAddress(addressRequest);
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("/getAll")
    public List<AddressResponse> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PutMapping("/update/{id}")
    public AddressResponse updateAddress(@PathVariable Integer id, @RequestBody AddressRequest addressRequest) {
        return addressService.updateAddress(id, addressRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
    }
}
