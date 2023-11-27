package com.example.Farmer.s.Delight.services;

import com.example.Farmer.s.Delight.data.model.Product;
import com.example.Farmer.s.Delight.dtos.request.AddProductRequest;

public interface ProductService {
    Product findProduct(String name, String type);

    void addProduct(AddProductRequest addProductRequest);
}
