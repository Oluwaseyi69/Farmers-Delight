package com.example.Farmer.s.Delight.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class Product {
    @DBRef
    private String productName;
    private String productType;
    private String price;
}
