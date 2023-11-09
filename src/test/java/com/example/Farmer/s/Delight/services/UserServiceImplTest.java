package com.example.Farmer.s.Delight.services;

import com.example.Farmer.s.Delight.data.model.User;
import com.example.Farmer.s.Delight.data.repository.UserRepository;
import com.example.Farmer.s.Delight.dtos.request.LoginRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterUserRequest;
import com.example.Farmer.s.Delight.exception.IncorrectCredentialsEx;
import com.example.Farmer.s.Delight.exception.UserAlreadyExistException;
import com.example.Farmer.s.Delight.exception.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void doThisFirst(){
        userRepository.deleteAll();
    }

    @Test
    public void testThatUserCanBeRegistered(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Anu");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void testForUniqueUser(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Anu");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("Anu");
        registerUserRequest1.setPassword("password");
        assertThrows(UserAlreadyExistException.class, ()-> userService.register(registerUserRequest1));
    }
    @Test
    public void testToLogInUser(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Anu");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Anu");
        loginRequest.setPassword("password");

        User user = userService.login(loginRequest);
        assertThat(user.isEndUserLoggedIn(), is(true));

    }
    @Test
    public void testThatUserCannotLoginWithIncorrectPassword(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Tomide");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Tomide");
        loginRequest.setPassword("pasword");
        assertThrows(IncorrectCredentialsEx.class,()->userService.login(loginRequest));
    }
    @Test
    public void testThatNonRegisteredUserCannotLogIn(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Tomide");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Seyi");
        loginRequest.setPassword("pasword");
        assertThrows(UserNotFound.class,()->userService.login(loginRequest));
    }
}