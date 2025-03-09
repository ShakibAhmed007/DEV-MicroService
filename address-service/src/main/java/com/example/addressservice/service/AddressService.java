package com.example.addressservice.service;

import com.example.addressservice.dto.AddressRequest;
import com.example.addressservice.dto.AddressResponse;
import com.example.addressservice.entity.Address;
import com.example.addressservice.exception.AddressNotFoundException;
import com.example.addressservice.repository.AddressRepository;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AddressResponse createAddress(AddressRequest addressRequest) {
        Address address = modelMapper.map(addressRequest, Address.class);
        address.setAddressId(null);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressResponse.class);
    }

    public AddressResponse getAddressById(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id: " + addressId));
        return modelMapper.map(address, AddressResponse.class);
    }

    public List<AddressResponse> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(address -> modelMapper.map(address, AddressResponse.class))
                .collect(Collectors.toList());
    }

    public AddressResponse updateAddress(Integer addressId, AddressRequest addressRequest) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id: " + addressId));

        address.setAddress(addressRequest.getAddress());
        address.setAddress2(addressRequest.getAddress2());
        address.setDistrict(addressRequest.getDistrict());
        address.setCityId(addressRequest.getCityId());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setPhone(addressRequest.getPhone());

        Address updatedAddress = addressRepository.save(address);
        return modelMapper.map(updatedAddress, AddressResponse.class);
    }

    public void deleteAddress(Integer addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new AddressNotFoundException("Address not found with id: " + addressId);
        }
        addressRepository.deleteById(addressId);
    }
}
