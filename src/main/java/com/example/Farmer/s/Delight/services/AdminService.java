package com.example.Farmer.s.Delight.services;

import com.example.Farmer.s.Delight.data.model.Admin;
import com.example.Farmer.s.Delight.dtos.request.AddProductRequest;
import com.example.Farmer.s.Delight.dtos.request.AdminLoginRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterAdminRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterAdminResponse;

public interface AdminService {
    RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest);

    Admin login(AdminLoginRequest adminLoginRequest );

    void add(AddProductRequest addProductRequest);
}
