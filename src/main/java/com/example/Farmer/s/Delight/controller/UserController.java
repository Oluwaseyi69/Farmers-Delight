package com.example.Farmer.s.Delight.controller;

import com.example.Farmer.s.Delight.dtos.request.LoginRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterUserRequest;
import com.example.Farmer.s.Delight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Farmer-Delight")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public String register(@RequestBody RegisterUserRequest registerUserRequest){
        try{
            userService.register(registerUserRequest);
            return "Registered Successfully";
        }catch (Exception error){
            return error.getMessage();
        }
    }
    @PostMapping("/LogIn")
    public String login(@RequestBody LoginRequest loginRequest){
        try{
            userService.login(loginRequest);
            return "Log-in successfully";
        }catch(Exception e){
            return e.getMessage();
        }
    }

}
