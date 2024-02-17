package com.farmbazaar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farmbazaar.model.entity.Farmer;
import com.farmbazaar.model.entity.Product;
import com.farmbazaar.model.repository.FarmerRepository;
import com.farmbazaar.model.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/farmers")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmerController {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{farmerId}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            return ResponseEntity.ok(farmer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{farmerId}/products")
    public ResponseEntity<List<Product>> getProductsByFarmerId(@PathVariable int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            List<Product> products = farmer.getProducts();
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{farmerId}/products/{productId}")
    public ResponseEntity<String> updateProductStock(@PathVariable int farmerId, @PathVariable int productId, @RequestParam double quantity) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            Product product = farmer.getProducts().stream()
                    .filter(p -> p.getId() == productId)
                    .findFirst()
                    .orElse(null);
            if (product != null) {
                double oldQuantity = product.getQuantity();
                double newQuantity = oldQuantity + quantity;
                product.setQuantity(newQuantity);
                productRepository.save(product);

                // Calculate profit earned from selling the product
                double profitEarned = quantity * product.getPrice() * 0.3;

                // Update total profit for the farmer
                double oldTotalProfit = farmer.getTotalProfit();
                double newTotalProfit = oldTotalProfit + profitEarned;
                farmer.setTotalProfit(newTotalProfit);
                farmerRepository.save(farmer);

                return ResponseEntity.ok("Product stock updated successfully. Profit earned: $" + profitEarned);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
