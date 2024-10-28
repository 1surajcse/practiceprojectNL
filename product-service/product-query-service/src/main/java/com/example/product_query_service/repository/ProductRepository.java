package com.example.product_query_service.repository;/*
 * @author -Suraj Tiwari
 */

import com.example.product_query_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
