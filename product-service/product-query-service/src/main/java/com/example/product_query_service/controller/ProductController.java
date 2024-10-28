package com.example.product_query_service.controller;/*
 * @author -Suraj Tiwari
 */

import com.example.product_query_service.entity.Product;
import com.example.product_query_service.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductQueryService productService;
    @GetMapping("/{id}")
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
