package com.example.springsecurity.login.service;

import com.example.springsecurity.registration.dto.UserDTO;
import com.example.springsecurity.registration.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsersService extends UserDetailsService {
    public User saveUser(UserDTO userDTO);
    UserDTO getUserDeailsByEmail(String email);
}
