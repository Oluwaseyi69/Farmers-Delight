package com.example.Farmer.s.Delight.data.repository;

import com.example.Farmer.s.Delight.data.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository  extends MongoRepository<Admin, String> {
   Optional<Admin> findAdminByUsername(String username);
}
