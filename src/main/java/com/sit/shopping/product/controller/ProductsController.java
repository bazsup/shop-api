package com.sit.shopping.product.controller;

import com.sit.shopping.product.dto.ProductsResponse;
import com.sit.shopping.product.model.Category;
import com.sit.shopping.product.model.Product;
import com.sit.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ProductsResponse getProducts(@RequestParam Optional<Category> category) {
        List<Product> products;
        if (category.isPresent()) {
            products = productRepository.findAllByCategory(category.get());
        } else {
            products = productRepository.findAll();
        }
        return new ProductsResponse(products);
    }
}
