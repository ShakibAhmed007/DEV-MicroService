package com.example.springsecurity.login;

import com.example.springsecurity.login.model.LoginRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestModel requestModel) {
        return ResponseEntity.ok("Login request received. Authentication is handled by the filter.");
    }
}
