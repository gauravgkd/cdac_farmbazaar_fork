/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This interface defines the repository for DeliveryPartner entities in the FarmBazaar application. It extends the JpaRepository interface provided by Spring Data JPA, allowing for basic CRUD operations on DeliveryPartner entities.
Last Modified: February 16, 2024
*/

package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {
    DeliveryPartner findByUsername(String username);
}
