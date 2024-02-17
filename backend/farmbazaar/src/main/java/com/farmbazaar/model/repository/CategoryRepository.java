/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This interface defines the repository for Category entities in the FarmBazaar application. It extends the JpaRepository interface provided by Spring Data JPA, allowing for basic CRUD operations on Category entities.
*/

package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // This interface inherits basic CRUD operations from JpaRepository
}
