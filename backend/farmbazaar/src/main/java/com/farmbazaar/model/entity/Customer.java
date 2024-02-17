/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class represents a customer entity in the FarmBazaar application. It extends the AbstractUser class and inherits common user properties such as username, password, name, phone number, address, role, and active status.
Last Modified: February 18, 2024
*/

package com.farmbazaar.model.entity;

import javax.persistence.*;

@Entity
public class Customer extends AbstractUser {
	
	@OneToOne(mappedBy = "customer")
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    // Additional attributes specific to Customer if any
}
