package com.example.springsecurity.registration.controller;

import com.example.springsecurity.registration.dto.UserCreatedResponseDTO;
import com.example.springsecurity.registration.dto.UserDTO;
import com.example.springsecurity.exception.ErrorResponse;
import com.example.springsecurity.registration.entity.User;
import com.example.springsecurity.registration.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignUpController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody UserDTO userDTO){
        try{
            User savedUser = userService.saveUser(userDTO);
            UserCreatedResponseDTO userCreatedResponseDTO = modelMapper.map(savedUser, UserCreatedResponseDTO.class);
            return new ResponseEntity<>(userCreatedResponseDTO, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), "Email already exists"));
        }
    }
}
