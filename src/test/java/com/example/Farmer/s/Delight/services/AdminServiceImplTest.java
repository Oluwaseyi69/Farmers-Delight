package com.example.Farmer.s.Delight.services;

import com.example.Farmer.s.Delight.data.model.Admin;
import com.example.Farmer.s.Delight.data.repository.AdminRepository;
import com.example.Farmer.s.Delight.data.repository.ProductRepository;
import com.example.Farmer.s.Delight.dtos.request.AddProductRequest;
import com.example.Farmer.s.Delight.dtos.request.AdminLoginRequest;
import com.example.Farmer.s.Delight.dtos.request.LoginRequest;
import com.example.Farmer.s.Delight.dtos.request.RegisterAdminRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp(){
        adminRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testThatMultipleAdminCanRegister(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setAdmin("Tomide");
        registerAdminRequest.setPassword("password");

        adminService.registerAdmin(registerAdminRequest);
        assertThat(adminRepository.count(), is(1L ));

        RegisterAdminRequest registerAdminRequest1 = new RegisterAdminRequest();
        registerAdminRequest1.setAdmin("laflare");
        registerAdminRequest1.setPassword("password");

        adminService.registerAdmin(registerAdminRequest1);
        assertThat(adminRepository.count(), is(2L));
    }

    @Test
    public void testThatRegisteredAdminCanLogin(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setAdmin("Seyi");
        registerAdminRequest.setPassword("password");

        adminService.registerAdmin(registerAdminRequest);
        assertThat(adminRepository.count(), is(1L ));

        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setAdmin("Seyi");
        adminLoginRequest.setPassword("password");

        Admin admin = adminService.login(adminLoginRequest);
        assertThat(admin.isLogIn(), is(true));
    }

    @Test
    public void testThatRegisteredAdminCanAddProduct(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setAdmin("Seyi");
        registerAdminRequest.setPassword("password");

        adminService.registerAdmin(registerAdminRequest);
        assertThat(adminRepository.count(), is(1L ));

        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setAdmin("Seyi");
        adminLoginRequest.setPassword("password");

//        Admin admin = adminService.login(adminLoginRequest);
//        assertThat(admin.isLogIn(), is(true));

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductName("Cucumber");
        addProductRequest.setProductType("Seed");
        adminService.add(addProductRequest);
        assertThat(productRepository.count(), is(1L));
    }



}