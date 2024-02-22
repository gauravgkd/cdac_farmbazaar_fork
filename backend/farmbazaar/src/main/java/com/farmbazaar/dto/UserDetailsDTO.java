/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class represents a Data Transfer Object (DTO) for user details, including user ID, username, and role.
*/

package com.farmbazaar.dto;

public class UserDetailsDTO {
    private int id;
    private String username;
    private String role;
    
    // Constructor, getters, and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}