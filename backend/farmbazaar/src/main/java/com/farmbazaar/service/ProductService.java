package com.farmbazaar.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.farmbazaar.model.entity.Product;
import com.farmbazaar.model.entity.Category; // Import Category entity
import com.farmbazaar.model.repository.ProductRepository;
import com.farmbazaar.model.repository.CategoryRepository; // Import CategoryRepository

import java.io.IOException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; // Inject CategoryRepository

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

        // Fetch the category based on the provided category ID
        Category category = fetchCategoryById(categoryId); // Use a method to fetch category by ID

        // Set the fetched category to the product
        product.setCategory(category);

        // Set other product attributes

        try {
            if (imageFile != null) {
                product.setImage(imageFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception
        }

        return productRepository.save(product);
    }

    // Method to fetch category by ID
    private Category fetchCategoryById(int categoryId) {
        // Implement logic to fetch category by ID from database
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
