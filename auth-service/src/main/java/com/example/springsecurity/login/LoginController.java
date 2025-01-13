package com.example.springsecurity.login;

import com.example.springsecurity.registration.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public User login(Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());
        if(user != null){
            return user;
        } else {
            return null;
        }
    }
}
