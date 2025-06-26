package com.example.users.service;

import com.example.users.model.User;
import com.example.users.model.Product;
import com.example.users.repository.UserRepository;
import com.example.users.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public StoreService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public User addUserWithProducts(int id, String name,int age, String email, List<Product> products) {
        User user = new User(id, name, age, email);

        for (Product product : products) {
            user.addProduct(product);
        }

        return userRepository.save(user); // Save user and associated products
    }

    public Optional<Product> findProductsByAuthor(Integer id) {
        return productRepository.findById(id);
    }
}