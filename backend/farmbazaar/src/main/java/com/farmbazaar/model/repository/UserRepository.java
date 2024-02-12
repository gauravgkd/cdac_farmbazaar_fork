package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<AbstractUser, Integer> {
    // Add custom query methods if needed

    // Example: Retrieve all active users
    //List<AbstractUser> findByIsActiveTrue();
}
