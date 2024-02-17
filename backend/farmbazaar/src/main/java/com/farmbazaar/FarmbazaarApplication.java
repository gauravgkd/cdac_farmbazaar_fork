/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class provides the starting point for the FarmBazaar Application. It initializes the Spring Boot application.
*/

package com.farmbazaar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FarmbazaarApplication {

    /**
     * Main method to start the FarmBazaar application.
     */
    public static void main(String[] args) {
        SpringApplication.run(FarmbazaarApplication.class, args);
    }

}
