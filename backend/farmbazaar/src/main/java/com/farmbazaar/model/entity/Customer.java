/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class represents a customer entity in the FarmBazaar application. It extends the AbstractUser class and inherits common user properties such as username, password, name, phone number, address, role, and active status.
*/

package com.farmbazaar.model.entity;

import javax.persistence.*;

@Entity
public class Customer extends AbstractUser {
    // Additional attributes specific to Customer if any
}
