package com.sit.shopping.product.repository;
//  implements ProductRepository

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.shopping.product.model.Product;

public interface ProductRepositoryDB extends JpaRepository<Product, String>, ProductRepository {
    
}
