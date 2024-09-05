package com.example.ECom_proj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ECom_proj.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
