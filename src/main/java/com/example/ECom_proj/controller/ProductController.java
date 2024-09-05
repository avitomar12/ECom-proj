package com.example.ECom_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECom_proj.model.Product;
import com.example.ECom_proj.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService service;
    
    public ProductController(ProductService service) {
        this.service = service;
    }
    

    @RequestMapping("/")
    public String greet(){
        return "Hello from ProductController";
    }
    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getAllProducts();
    }
}
