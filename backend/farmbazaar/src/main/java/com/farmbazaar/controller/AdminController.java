package com.farmbazaar.controller;

import com.farmbazaar.model.entity.*;
import com.farmbazaar.model.repository.*;
import com.farmbazaar.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductService productService;

    // CRUD operations for Admin users

    @PostMapping("/admin-users")
    public Admin createAdminUser(@RequestBody Admin user) {
        return adminRepository.save(user);
    }

    @PutMapping("/admin-users/{id}")
    public Admin updateAdminUser(@PathVariable int id, @RequestBody Admin userDetails) {
        Admin user = adminRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return adminRepository.save(user);
        }
        return null; // Handle not found
    }


    @DeleteMapping("/admin-users/{id}")
    public void deleteAdminUser(@PathVariable int id) {
        adminRepository.deleteById(id);
    }

    @GetMapping("/admin-users")
    public List<Admin> getAllAdminUsers() {
        return adminRepository.findAll();
    }

    // CRUD operations for Farmer users

    @PostMapping("/farmer-users")
    public Farmer createFarmerUser(@RequestBody Farmer user) {
        return farmerRepository.save(user);
    }

    @PutMapping("/farmer-users/{id}")
    public Farmer updateFarmerUser(@PathVariable int id, @RequestBody Farmer userDetails) {
        Farmer user = farmerRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return farmerRepository.save(user);
        }
        return null; // Handle not found
    }

    @DeleteMapping("/farmer-users/{id}")
    public void deleteFarmerUser(@PathVariable int id) {
        farmerRepository.deleteById(id);
    }

    @GetMapping("/farmer-users")
    public List<Farmer> getAllFarmerUsers() {
        return farmerRepository.findAll();
    }

    // CRUD operations for Delivery Partner users

    @PostMapping("/delivery-partner-users")
    public DeliveryPartner createDeliveryPartnerUser(@RequestBody DeliveryPartner user) {
        return deliveryPartnerRepository.save(user);
    }

    @PutMapping("/delivery-partner-users/{id}")
    public DeliveryPartner updateDeliveryPartnerUser(@PathVariable int id, @RequestBody DeliveryPartner userDetails) {
        DeliveryPartner user = deliveryPartnerRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return deliveryPartnerRepository.save(user);
        }
        return null; // Handle not found
    }


    @DeleteMapping("/delivery-partner-users/{id}")
    public void deleteDeliveryPartnerUser(@PathVariable int id) {
        deliveryPartnerRepository.deleteById(id);
    }

    @GetMapping("/delivery-partner-users")
    public List<DeliveryPartner> getAllDeliveryPartnerUsers() {
        return deliveryPartnerRepository.findAll();
    }

    // CRUD operations for Customer users

    @PostMapping("/customer-users")
    public Customer createCustomerUser(@RequestBody Customer user) {
        return customerRepository.save(user);
    }

    @PutMapping("/customer-users/{id}")
    public Customer updateCustomerUser(@PathVariable int id, @RequestBody Customer userDetails) {
        Customer user = customerRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFname(userDetails.getFname());
            user.setLname(userDetails.getLname());
            user.setPhno(userDetails.getPhno());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setActive(userDetails.isActive());
            
            // Update other user details as needed
            
            return customerRepository.save(user);
        }
        return null; // Handle not found
    }


    @DeleteMapping("/customer-users/{id}")
    public void deleteCustomerUser(@PathVariable int id) {
        customerRepository.deleteById(id);
    }

    @GetMapping("/customer-users")
    public List<Customer> getAllCustomerUsers() {
        return customerRepository.findAll();
    }

    // CRUD operations for categories

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(categoryDetails.getName());
            // Update other category details as needed
            return categoryRepository.save(category);
        }
        return null; // Handle not found
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryRepository.deleteById(id);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // CRUD operations for products
//    @PostMapping("/products")
//    public Product createProduct(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("quantity") double quantity, @RequestParam("pre_order_quantity") double preOrderQuantity) {
//        try {
//            // Convert MultipartFile to byte array and save the image data
//            byte[] imageData = imageFile.getBytes();
//            // Create a new Product object with image data
//            Product product = new Product();
//            product.setImage(imageData);
//            product.setName(name);
//            product.setPrice(price);
//            product.setQuantity(quantity);
//            product.setPre_order_quantity(preOrderQuantity);
//            // Save the product to the database
//            return productRepository.save(product);
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle error
//            return null;
//        }
//    }
    
    @PostMapping("/products")
    public Product createProduct(@RequestParam("name") String name,
                                 @RequestParam("price") double price,
                                 @RequestParam("quantity") double quantity,
                                 @RequestParam("pre_order_quantity") double preOrderQuantity,
                                 @RequestParam("category_id") int categoryId,
                                 @RequestParam("imageFile") MultipartFile imageFile) {
        return productService.createProduct(name, price, quantity, preOrderQuantity, categoryId, imageFile);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("quantity") double quantity, @RequestParam("pre_order_quantity") double preOrderQuantity) {
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product != null) {
                // Update image data if provided
                if (!imageFile.isEmpty()) {
                    byte[] imageData = imageFile.getBytes();
                    product.setImage(imageData);
                }
                // Update other product details
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setPre_order_quantity(preOrderQuantity);
                // Save the updated product to the database
                return productRepository.save(product);
            }
            return null; // Handle not found
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
            return null;
        }
    }


    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
    // Assign product to farmer

    @PostMapping("/assign/{farmerId}")
    public void assignProductsToFarmer(@PathVariable Integer farmerId, @RequestBody List<Integer> productIds) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            List<Product> products = productRepository.findAllById(productIds);
            products.forEach(product -> farmer.getProducts().add(product));
            farmerRepository.save(farmer);
        }
    }

    // Endpoint to get products assigned to a specific farmer
    @GetMapping("/{farmerId}/products")
    public List<Product> getProductsByFarmerId(@PathVariable int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer != null) {
            return farmer.getProducts();
        } else {
            // Handle case where farmer with given ID is not found
            return null; // Or return an empty list or throw an exception as per your requirement
        }
    }


}
