package com.farmbazaar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbazaar.model.entity.Product;
import com.farmbazaar.model.repository.ProductRepository;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
    private ProductRepository productRepository;
	
	@GetMapping("/products")
    public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
        // Encode image data to Base64 before sending
        products.forEach(Product::encodeImageDataToBase64);
        return products;
    }
}
