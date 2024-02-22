/*
Author: Shubham Samarth
Date: February 16, 2024
Description: This class provides service methods for user authentication, allowing users to log in to the system securely.
Last Modified: February 22, 2024*/

package com.farmbazaar.controller;

import com.farmbazaar.service.LoginService;
import com.farmbazaar.service.LoginService.LoginRequest;
import com.farmbazaar.model.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        AbstractUser user = loginService.authenticateUser(loginRequest);
        if (user != null) {
            return ResponseEntity.ok(user); // Return user data if login successful
        }

        // Authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
