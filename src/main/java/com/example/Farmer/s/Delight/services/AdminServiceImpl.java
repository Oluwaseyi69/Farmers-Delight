package com.example.Farmer.s.Delight.services;

import com.example.Farmer.s.Delight.data.model.Admin;
import com.example.Farmer.s.Delight.data.repository.AdminRepository;
import com.example.Farmer.s.Delight.dtos.request.AddProductRequest;
import com.example.Farmer.s.Delight.dtos.request.AdminLoginRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterAdminRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterAdminResponse;
import com.example.Farmer.s.Delight.exception.AdminAlreadyExistException;
import com.example.Farmer.s.Delight.exception.IncorrectCredentialsEx;
import com.example.Farmer.s.Delight.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.Farmer.s.Delight.utils.AdminMapper.map;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductService productService;
    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) {
        findAdmin(registerAdminRequest.getAdmin());
        return map(adminRepository.save(map(registerAdminRequest)));
    }

    @Override
    public Admin login(AdminLoginRequest adminLoginRequest) {
        Optional<Admin> admin = getAdmin(adminLoginRequest.getAdmin());
        if(admin.isEmpty()) throw new UserNotFound("Admin not found");
        if(!admin.get().getPassword().equals(adminLoginRequest.getPassword()))
            throw new IncorrectCredentialsEx("Incorrect Credentials");


        admin.get().setLogIn(true);
        adminRepository.save(admin.get());
        return admin.get();

    }

    @Override
    public void add(AddProductRequest addProductRequest) {
        productService.addProduct(addProductRequest);
    }

    private Optional<Admin> getAdmin(String admin) {
        Optional<Admin> user = adminRepository.findAdminByUsername(admin);
        return user;
    }


    private void findAdmin(String username) {
        Optional<Admin> admin = adminRepository.findAdminByUsername(username);
        if(admin.isPresent()){
            throw new AdminAlreadyExistException("Admin Already Exist");
        }
    }


}
