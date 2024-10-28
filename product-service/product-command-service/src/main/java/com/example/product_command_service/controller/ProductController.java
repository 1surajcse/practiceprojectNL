package com.example.product_command_service.controller;/*
 * @author -Suraj Tiwari
 */

import com.example.dto.ProductEvent;
import com.example.product_command_service.entity.Product;
import com.example.product_command_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@EnableCaching
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductEvent productEvent){

        Product product1 =productService.save(productEvent);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody ProductEvent productEvent){

        Product product1 =productService.updateProduct(id,productEvent);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product product1 =productService.findById(id);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products =productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
