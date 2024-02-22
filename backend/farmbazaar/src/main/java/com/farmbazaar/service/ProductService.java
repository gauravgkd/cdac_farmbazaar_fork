/*
Author: Shubham Samarth
Date: February 14, 2024
Description: This class allows for the creation of new products by providing necessary parameters such as name, price, quantity, category ID, and an optional image file
Last Modified: February 16, 2024
*/

package com.farmbazaar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.farmbazaar.model.entity.Product;
import com.farmbazaar.model.entity.Category;
import com.farmbazaar.model.repository.ProductRepository;
import com.farmbazaar.model.repository.CategoryRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createProduct(String name, double price, double quantity, double preOrderQuantity,
                                 int categoryId, MultipartFile imageFile) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPre_order_quantity(preOrderQuantity);

        Category category = fetchCategoryById(categoryId);
        product.setCategory(category);

        try {
            if (imageFile != null) {
                product.setImage(imageFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productRepository.save(product);
    }

    public Product updateProduct(int id, MultipartFile imageFile, String name, double price, double quantity, double preOrderQuantity) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            try {
                if (imageFile != null) {
                    product.setImage(imageFile.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setPre_order_quantity(preOrderQuantity);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private Category fetchCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
