package com.example.springsecurity.registration.services;

import com.example.springsecurity.login.service.IUsersService;
import com.example.springsecurity.registration.dto.UserDTO;
import com.example.springsecurity.registration.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public User saveUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserDeailsByEmail(String email) {
        User userEntity = userRepository.findByEmail(email);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new ModelMapper().map(userEntity, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }


}
