package com.example.ECom_proj.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ECom_proj.model.Product;
import com.example.ECom_proj.service.ProductService;

@RestController
@CrossOrigin
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
    public ResponseEntity <List<Product>> getProducts(){
        return new ResponseEntity<>(service. getAllProducts(), HttpStatus.OK);
    }



    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }



    @PostMapping("/product")
    public ResponseEntity<?> addproduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
        Product product1= service.addProduct(product,imageFile);
        return new ResponseEntity<>(product1,HttpStatus.CREATED);
    }
    catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }




    @PostMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
        Product product = service.getProductById(id);
        byte[] image = product.getImageDate();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(image);
    }




    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile){
       Product product1=null;
       try{
        product1= service.updateProduct(id,product,imageFile);
        
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
       if(product1!=null){
           return new ResponseEntity<>("Product updated successfully",HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){

        Product product = service.getProductById(id);
        if(product!= null){

            service.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> getProducts(@RequestPart String Keyword){
        List<Product> products = service.searchProducts(Keyword);

        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
