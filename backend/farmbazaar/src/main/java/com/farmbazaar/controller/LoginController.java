package com.farmbazaar.controller;

import com.farmbazaar.enums.Role;
import com.farmbazaar.model.entity.AbstractUser;
import com.farmbazaar.model.repository.AdminRepository;
import com.farmbazaar.model.repository.CustomerRepository;
import com.farmbazaar.model.repository.DeliveryPartnerRepository;
import com.farmbazaar.model.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        AbstractUser user = findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok(user); // Return user data if login successful
        }

        // Authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    private AbstractUser findUserByUsername(String username) {
        AbstractUser user = adminRepository.findByUsername(username);
        if (user == null) {
            user = farmerRepository.findByUsername(username);
        }
        if (user == null) {
            user = customerRepository.findByUsername(username);
        }
        if (user == null) {
            user = deliveryPartnerRepository.findByUsername(username);
        }
        return user;
    }

    static class LoginRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
