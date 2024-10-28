package com.example.product_command_service.service;/*
 * @author -Suraj Tiwari
 */

import com.example.dto.ProductDto;
import com.example.dto.ProductEvent;
import com.example.product_command_service.entity.Product;
import com.example.product_command_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    public Product save(ProductEvent productEvent) {
        ProductDto product=productEvent.getProduct();

        Product product1=new Product();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setBrand(product.getBrand());

       Product productRes=productRepository.save(product1);
       ProductEvent productEvent1=new ProductEvent();
       productEvent1.setEventType(productEvent.getEventType());
       productEvent1.setProduct(ProductDto.builder()
               .brand(productRes.getBrand())
               .name(productRes.getName())
               .price(productRes.getPrice()).build());
       kafkaTemplate.send("product-topic",productEvent1);
        return  productRes;
    }
    public Product updateProduct(Long id,ProductEvent productEvent) {
        ProductDto productDto=productEvent.getProduct();

       Product existingProduct=productRepository.findById(id).get();
       existingProduct.setBrand(productDto.getBrand());
       existingProduct.setName(productDto.getName());
       existingProduct.setPrice(productDto.getPrice());
        Product productRes=productRepository.save(existingProduct);
        ProductEvent productEvent2=new ProductEvent();
        productEvent2.setEventType(productEvent.getEventType());
        productEvent2.setProduct(ProductDto.builder()
                .brand(productRes.getBrand())
                .name(productRes.getName())
                .id(productRes.getProductId())
                .price(productRes.getPrice()).build());
        kafkaTemplate.send("product-topic",productEvent2);
        return productRes;

    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
