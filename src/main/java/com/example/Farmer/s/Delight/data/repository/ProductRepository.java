package com.example.Farmer.s.Delight.data.repository;

import com.example.Farmer.s.Delight.data.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
