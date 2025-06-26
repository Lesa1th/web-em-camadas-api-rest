package com.example.users.api;

import com.example.users.dto.ProductRequest;
import com.example.users.model.User;
import com.example.users.model.Product;
import com.example.users.service.UserService;
import com.example.users.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final UserService userService;
    private final ProductService productService;

    public StoreController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.addUser(user.getId(),user.getName(),user.getAge(), user.getEmail());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> users = productService.getAllProducts();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestParam String name, @RequestParam int age, @RequestParam String email) {
        User updatedUser = userService.updateUser(id, name, age, email);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
        Product newProduct = new Product(productRequest.getId(),
                productRequest.getName(),
                productRequest.getRelease_date());
        Product createdProduct = productService.addProductToUser(
                productRequest.getUserId(),
                newProduct
        );
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/{id}/products")
    public ResponseEntity<Product> addProductToUser(
            @PathVariable int id,
            @RequestBody ProductRequest productRequest) {
        Product newProduct = new Product(productRequest.getId(),
                productRequest.getName(),
                productRequest.getRelease_date());
        Product createdProduct = productService.addProductToUser(
                id,
                newProduct
        );
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

}