/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class represents a farmer entity in the FarmBazaar application. It extends the AbstractUser class and inherits common user properties such as username, password, name, phone number, address, role, and active status. Additionally, it contains specific attributes related to farmers such as the list of products they offer and their total profit.
*/

package com.farmbazaar.model.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Farmer extends AbstractUser {
    // Farmer specific attributes, if any

    @ManyToMany
    @JoinTable(name = "farmer_product",
               joinColumns = @JoinColumn(name = "farmer_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnore // Exclude products from serialization to prevent recursion
    private List<Product> products;

    @Column(name = "total_profit")
    private double totalProfit;
    
    // Getters and setters

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    // toString method for debugging and logging
    
    @Override
    public String toString() {
        return "Farmer [products=" + products + ", totalProfit=" + totalProfit + ", getProducts()=" + getProducts()
                + ", getTotalProfit()=" + getTotalProfit() + ", getId()=" + getId() + ", getUsername()=" + getUsername()
                + ", getPassword()=" + getPassword() + ", getFname()=" + getFname() + ", getLname()=" + getLname()
                + ", getPhno()=" + getPhno() + ", getAddress()=" + getAddress() + ", getRole()=" + getRole()
                + ", isActive()=" + isActive() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + "]";
    } 
}
