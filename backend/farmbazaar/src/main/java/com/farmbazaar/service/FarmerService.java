/*
Author: Shubham Samarth
Date: February 21, 2024
Description: This class provides service methods for farmer-related operations, including retrieving farmer information, managing products associated with a farmer, and updating product stock.
Last Modified: February 22, 2024
*/

package com.farmbazaar.service;

import com.farmbazaar.model.entity.Farmer;
import com.farmbazaar.model.entity.Product;
import com.farmbazaar.model.repository.FarmerRepository;
import com.farmbazaar.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private ProductRepository productRepository;

    public Farmer getFarmerById(int farmerId) {
        return farmerRepository.findById(farmerId).orElse(null);
    }

    public List<Product> getProductsByFarmerId(int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            return farmer.getProducts();
        }
        return null;
    }

    public String updateProductStock(int farmerId, int productId, double quantity) {
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

                return "Product stock updated successfully. Profit earned: $" + profitEarned;
            } else {
                return "Error: Product not found";
            }
        } else {
            return "Error: Farmer not found";
        }
    }
}
