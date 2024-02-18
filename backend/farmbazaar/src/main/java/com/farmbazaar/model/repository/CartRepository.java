/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This class is an interface that extends JpaRepository to provide CRUD (Create, Read, Update, Delete) operations for the Cart entity in the FarmBazaar application. It allows interaction with the underlying database table representing the Cart entity.
*/
package com.farmbazaar.model.repository;
import com.farmbazaar.model.entity.Cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	Optional<Cart> findByCustomer_Id(int customerId);
}

