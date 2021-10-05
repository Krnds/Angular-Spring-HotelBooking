package com.karinedias.hotelapp.controller;


import com.karinedias.hotelapp.entity.Admin;
import com.karinedias.hotelapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AdminRepository adminRepo;

    public LoginController(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Admin> checkLogin(@RequestBody Admin user) {
        System.out.println("in check login");
        try {
            System.out.println("LoginController, user in checkLogin = " + user);
            Admin admin = adminRepo.findByUsername(user.getUsername());
            admin.setPassword("");
            return ResponseEntity.ok()
                    .body(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
