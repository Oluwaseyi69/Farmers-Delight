package com.example.Farmer.s.Delight.services;

import com.example.Farmer.s.Delight.data.model.User;
import com.example.Farmer.s.Delight.data.repository.UserRepository;
import com.example.Farmer.s.Delight.dtos.request.LoginRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterUserRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterUserResponse;
import com.example.Farmer.s.Delight.exception.IncorrectCredentialsEx;
import com.example.Farmer.s.Delight.exception.UserAlreadyExistException;
import com.example.Farmer.s.Delight.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.Farmer.s.Delight.utils.Mapper.map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        findUser(registerUserRequest);
        return map(userRepository.save(map(registerUserRequest)));
    }

    @Override
    public User login(LoginRequest loginRequest) {
        Optional<User> user = getUser(loginRequest.getUsername());
        if(user.isEmpty()) throw new UserNotFound("User Not Found");
        if(!user.get().getPassword().equals(loginRequest.getPassword()))
            throw new IncorrectCredentialsEx("Incorrect Credentials");

        userRepository.delete(user.get());
        User user1 = user.get();
        user1.setEndUserLoggedIn(true);
        userRepository.save(user1);
        return user1;

    }

    private void findUser(RegisterUserRequest registerUserRequest){
        Optional<User> user = userRepository.findUserByEndUsername(registerUserRequest.getUsername());
        if(user.isPresent())
            throw new UserAlreadyExistException("Farmer Already Exist");
    }
    private Optional<User> getUser(String username){
        Optional<User> user = userRepository.findUserByEndUsername(username);
        return user;
    }
}
