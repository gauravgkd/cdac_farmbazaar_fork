/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This interface defines the repository for AbstractUser entities in the FarmBazaar application. It extends the JpaRepository interface provided by Spring Data JPA, allowing for basic CRUD operations on AbstractUser entities.
*/

package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<AbstractUser, Integer> {
    
}
