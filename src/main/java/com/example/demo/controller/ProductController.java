package com.example.demo.controller;

import Exceptions.ResourceNotFoundException;
import com.example.demo.Models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // create get all employees
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    //create employee
    @PostMapping("/products")
    public Product createProduct(@Validated @RequestBody Product product) {
        return productRepository.save(product);
    }
    //get employee by id
    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") int productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Emloyee not found for this id :: " +productId ));
        return ResponseEntity.ok().body(product);
    }
    //update employee
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") int productId, @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Emloyee not found for this id :: " +productId ));
        product.setNom(productDetails.getNom());
        product.setPrix(productDetails.getPrix());
        product.setCategorie(productDetails.getCategorie());
        product.setPromotions(productDetails.getPromotions());
        product.setQuantite(productDetails.getQuantite());
        product.setImage(productDetails.getImage());
        productRepository.save(product);
        return ResponseEntity.ok().body(product);
    }
    //delete employee by id

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") int productId) throws ResourceNotFoundException {

        productRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }

}
