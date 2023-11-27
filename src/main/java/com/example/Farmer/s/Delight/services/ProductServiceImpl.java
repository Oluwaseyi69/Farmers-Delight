package com.example.Farmer.s.Delight.services;

import com.example.Farmer.s.Delight.data.model.Product;
import com.example.Farmer.s.Delight.data.repository.ProductRepository;
import com.example.Farmer.s.Delight.dtos.request.AddProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.Farmer.s.Delight.utils.Mapper.map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product findProduct(String name, String type) {
        return null;
    }

    @Override
    public void addProduct(AddProductRequest addProductRequest) {
        Product product = map(addProductRequest);
        productRepository.save(product);

    }
}
