/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This class defines REST endpoints for customer-specific operations, such as retrieving all products available for purchase.
Last Modified: February 19, 2024
*/

package com.farmbazaar.controller;

import com.farmbazaar.dto.CartItemRequest;
import com.farmbazaar.dto.CheckoutRequest;
import com.farmbazaar.model.entity.*;
import com.farmbazaar.model.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository; 

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        // Encode image data to Base64 before sending
        products.forEach(Product::encodeImageDataToBase64);
        return products;
    }
    
//    @PostMapping("/cart/{customerId}/add")
//    public ResponseEntity<?> addToCart(@PathVariable int customerId, @RequestBody CartItemRequest cartItemRequest) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if (!optionalCustomer.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
//        }
//        Customer customer = optionalCustomer.get();
//        
//        // Check if the customer has a cart, create a new one if not
//        Cart cart = customer.getCart();
//        if (cart == null) {
//            cart = new Cart();
//            cart.setCustomer(customer);
//            customer.setCart(cart); // Associate cart with customer
//        }
//        
//        Optional<Product> optionalProduct = productRepository.findById(cartItemRequest.getProductId());
//        if (!optionalProduct.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//        }
//        Product product = optionalProduct.get();
//        
//        // Create new CartItem and add it to the cart
//        CartItem cartItem = new CartItem();
//        cartItem.setProduct(product);
//        cartItem.setQuantity(cartItemRequest.getQuantity());
//        cartItem.setPrice(product.getPrice());
//        cart.addCartItem(cartItem); // Associate the CartItem with the Cart
//        
//        cart.calculateTotalPrice();
//        
//        cartRepository.save(cart);
//        
//        return ResponseEntity.ok("Product added to cart successfully");
//    }
    
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
        
        // Check if the cart already contains the product, update quantity if it does
        CartItem existingCartItem = cart.getCartItemByProductId(product.getId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
        } else {
            // Create new CartItem and add it to the cart
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice());
            cart.addCartItem(cartItem); // Associate the CartItem with the Cart
        }
        
        cart.calculateTotalPrice();
        
        cartRepository.save(cart);
        
        return ResponseEntity.ok("Product added to cart successfully");
    }


//    @GetMapping("/cart/{customerId}/items")
//    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int customerId) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if (!optionalCustomer.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        Customer customer = optionalCustomer.get();
//        
//        Cart cart = customer.getCart();
//        if (cart == null) {
//            return ResponseEntity.ok(new ArrayList<>()); // Return an empty list if cart is null
//        }
//        
//        List<CartItem> cartItems = cart.getCartItems();
//        
//        return ResponseEntity.ok(cartItems);
//    }  

    @GetMapping("/cart/{customerId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Customer customer = optionalCustomer.get();
        
        Cart cart = customer.getCart();
        if (cart == null) {
            return ResponseEntity.ok(new ArrayList<>()); // Return an empty list if cart is null
        }
        
        List<CartItem> cartItems = cart.getCartItems();
        
        // Encode image data to Base64 for each product
        List<CartItem> cartItemsWithBase64Images = cartItems.stream()
                .peek(item -> item.getProduct().encodeImageDataToBase64()) // Ensure that imageBase64 is populated
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartItemsWithBase64Images);
    }
    
    @PostMapping("/cart/{customerId}/checkout")
    public ResponseEntity<?> checkoutCart(@PathVariable int customerId, @RequestBody CheckoutRequest checkoutRequest) {
        // Retrieve customer and cart
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        Customer customer = optionalCustomer.get();
        Cart cart = customer.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty");
        }

        // Create a new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(cart.getTotalPrice()); // Set total amount from cart
        order.setOrderStatus("Pending"); // Set initial order status
        order.setDeliveryStatus("Pending"); // Set initial delivery status
        order.setDeliveryAddress(checkoutRequest.getDeliveryAddress()); // Set delivery address

        // Set expected delivery date
        Date expectedDeliveryDate;
        if (checkoutRequest.getOrderType().equals("pre-order")) {
            String deliveryDateStr = checkoutRequest.getDeliveryDate();
            if (deliveryDateStr != null && !deliveryDateStr.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    expectedDeliveryDate = sdf.parse(deliveryDateStr);
                    order.setExpectedDeliveryDate(expectedDeliveryDate);
                } catch (ParseException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid delivery date format");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delivery date is required for pre-orders");
            }
        } else {
            // Calculate next to next day
            Calendar nextToNextDay = Calendar.getInstance();
            nextToNextDay.add(Calendar.DAY_OF_MONTH, 2);
            expectedDeliveryDate = nextToNextDay.getTime();
            order.setExpectedDeliveryDate(expectedDeliveryDate);
        }

        // Set placed date
        order.setPlacedDate(new Date());

        // Save the order
        orderRepository.save(order);

        // Create order items from cart items
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItemRepository.save(orderItem);
        }

        // Clear the cart
        cart.getCartItems().clear();
        cart.calculateTotalPrice();
        cartRepository.save(cart);

        return ResponseEntity.ok("Order placed successfully");
    }

}
