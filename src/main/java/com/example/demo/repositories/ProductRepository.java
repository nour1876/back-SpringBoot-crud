package com.example.demo.repositories;

import com.example.demo.Models.Product;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
@EnableJpaRepositories
public interface ProductRepository extends CrudRepository<Product, Integer> {


}

