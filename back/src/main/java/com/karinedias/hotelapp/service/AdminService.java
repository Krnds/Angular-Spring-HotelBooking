package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Admin;
import com.karinedias.hotelapp.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private AdminRepository adminRepo;
    private final PasswordEncoder encoder;

    public AdminService(AdminRepository adminRepo, PasswordEncoder encoder) {
        this.adminRepo = adminRepo;
        this.encoder = encoder;
    }

    public List<Admin> findAll() {
        return (List<Admin>) adminRepo.findAll();
    }

    public Admin findById(int id) {
        return adminRepo.findById(id).get();
    }
}
