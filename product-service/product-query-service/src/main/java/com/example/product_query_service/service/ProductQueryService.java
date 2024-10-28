package com.example.product_query_service.service;/*
 * @author -Suraj Tiwari
 */




import com.example.dto.ProductDto;
import com.example.dto.ProductEvent;
import com.example.product_query_service.entity.Product;
import com.example.product_query_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @KafkaListener(topics = "product-topic",groupId = "product-group")
    public  void processProductEvent(ProductEvent event){
        ProductDto productDto=event.getProduct();
        Product product=new Product();
        product.setBrand(productDto.getBrand());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        String eventType=event.getEventType();
        if(eventType.equals("Create"))
        {
            productRepository.save(product);
        }
        if(eventType.equals("Update")) {
            System.out.println("inside update....");
         Product existingProduct= productRepository.findById(productDto.getId()).get();
            existingProduct.setBrand(product.getBrand());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
        }
    }

}




