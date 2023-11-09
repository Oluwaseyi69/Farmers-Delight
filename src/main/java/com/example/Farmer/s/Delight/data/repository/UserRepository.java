package com.example.Farmer.s.Delight.data.repository;

import com.example.Farmer.s.Delight.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEndUsername(String username);
}
