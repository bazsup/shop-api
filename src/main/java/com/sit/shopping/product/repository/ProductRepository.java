package com.sit.shopping.product.repository;

import com.sit.shopping.product.model.Category;
import com.sit.shopping.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String productId);

    List<Product> findAll();
    List<Product> findAllByCategory(Category category);
}
