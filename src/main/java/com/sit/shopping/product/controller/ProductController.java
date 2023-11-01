package com.sit.shopping.product.controller;

import com.sit.shopping.exception.EntityNotFoundException;
import com.sit.shopping.product.model.Product;
import com.sit.shopping.product.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        LOGGER.info("Getting Product with Product Id {}", id);
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A product cannot be found"));
    }
}
