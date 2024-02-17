/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This interface defines the repository for Product entities in the FarmBazaar application. It extends the JpaRepository interface provided by Spring Data JPA, allowing for basic CRUD operations on Product entities.
*/

package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
