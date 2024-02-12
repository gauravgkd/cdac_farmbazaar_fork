package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {
    // Add custom query methods if needed
}
