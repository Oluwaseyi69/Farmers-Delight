package com.example.Farmer.s.Delight.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Admin {
    @Id
    private int id;
    private String username;
    private String password;
    private String isLoggedIn;

}
