/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This class represents a request to add an item to the cart in the FarmBazaar application. 
             It contains information about the product ID and the quantity to be added.
*/

package com.farmbazaar.dto;

public class CartItemRequest {
    private int productId;
    private int quantity;

    // Getters and setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
