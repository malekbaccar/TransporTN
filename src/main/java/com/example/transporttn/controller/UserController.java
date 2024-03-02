package com.example.transporttn.controller;

import com.example.transporttn.dto.LoginRequest;
import com.example.transporttn.entites.Account;
import com.example.transporttn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public Account registerUser(@RequestBody Account user) {
        userService.registerUser(user);
        return user;
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return userService.loginUser(email, password);
    }

}