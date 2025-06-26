package com.example.users.service;

import com.example.users.model.Product;
import com.example.users.model.User;
import com.example.users.repository.ProductRepository;
import com.example.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public Product addProductToUser(int userId, Product product) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + userId));
        Product product1 = new Product(product.getId(), product.getName(), product.getRelease_date());
        product1.setUser(user);


        return productRepository.save(product1);
    }
}