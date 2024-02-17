/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This class defines REST endpoints for customer-specific operations, such as retrieving all products available for purchase.
*/

package com.farmbazaar.controller;

import com.farmbazaar.dto.CartItemRequest;
import com.farmbazaar.model.entity.*;
import com.farmbazaar.model.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        // Encode image data to Base64 before sending
        products.forEach(Product::encodeImageDataToBase64);
        return products;
    }
    
    @PostMapping("/cart/{customerId}/add")
    public ResponseEntity<?> addToCart(@PathVariable int customerId, @RequestBody CartItemRequest cartItemRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        Customer customer = optionalCustomer.get();
        
        // Check if the customer has a cart, create a new one if not
        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart); // Associate cart with customer
        }
        
        Optional<Product> optionalProduct = productRepository.findById(cartItemRequest.getProductId());
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        Product product = optionalProduct.get();
        
        // Create new CartItem and add it to the cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setPrice(product.getPrice());
        cart.addCartItem(cartItem); // Associate the CartItem with the Cart
        
        cart.calculateTotalPrice();
        
        cartRepository.save(cart);
        
        return ResponseEntity.ok("Product added to cart successfully");
    }

    
    // Other methods for updating, deleting, and managing carts and orders
    
}
