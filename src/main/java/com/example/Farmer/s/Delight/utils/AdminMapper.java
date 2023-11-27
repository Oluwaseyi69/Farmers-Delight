package com.example.Farmer.s.Delight.utils;

import com.example.Farmer.s.Delight.data.model.Admin;
import com.example.Farmer.s.Delight.dtos.request.RegisterAdminRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterAdminResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminMapper {
    public static Admin map(RegisterAdminRequest registerAdminRequest){
        Admin admin = new Admin();
        admin.setUsername(registerAdminRequest.getAdmin());
        admin.setPassword(registerAdminRequest.getPassword());

        return admin;
    }
    public static RegisterAdminResponse map(Admin admin){
        RegisterAdminResponse registerAdminResponse = new RegisterAdminResponse();
        registerAdminResponse.setAdminName(admin.getUsername());
        registerAdminResponse.setDateCreated(DateTimeFormatter
                .ofPattern("EEE dd/MMM/yyyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        return registerAdminResponse;
    }

}
