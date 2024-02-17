/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This interface defines the repository for Admin entities in the FarmBazaar application. It extends the JpaRepository interface provided by Spring Data JPA and provides custom methods to interact with the Admin entity, such as finding an Admin by username.
Last Modified: February 16, 2024
*/

package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    // Method to find an Admin entity by username
    Admin findByUsername(String username);
}
