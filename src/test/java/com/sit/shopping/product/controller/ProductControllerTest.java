package com.sit.shopping.product.controller;

import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sit.shopping.product.model.Category;
import com.sit.shopping.product.model.Product;
import com.sit.shopping.product.repository.ProductRepository;

class ProductControllerTest {
    private ProductController underTest;
    private ProductRepository mockProductRepository;

    @BeforeEach
    void setUp() {
        underTest = new ProductController();

        mockProductRepository = Mockito.mock(ProductRepository.class);
        underTest.setProductRepository(mockProductRepository);
    }

    @Test
    void testGetProductById() {
        String expectedId = "id-product-001";
        Product expected = Product.create("p1", 10.0, "https://image.com/p1.png", Category.MEN);
        expected.setId(expectedId);

        Mockito.when(mockProductRepository.findById(expectedId)).thenReturn(Optional.of(expected));

        Optional<Product> product = underTest.getProductById(expectedId);

        MatcherAssert.assertThat(product.get().getId(), CoreMatchers.equalTo(expected.getId()));
        MatcherAssert.assertThat(product.get().getName(), CoreMatchers.equalTo(expected.getName()));
        MatcherAssert.assertThat(product.get().getPrice(), CoreMatchers.equalTo(expected.getPrice()));
        MatcherAssert.assertThat(product.get().getImageUrl(), CoreMatchers.equalTo(expected.getImageUrl()));
    }

    // @Test
    // void testGetProductByIdButNotFound() {
    //     String expectedId = "id-product-001";

    //     Mockito.when(mockProductRepository.findById(expectedId)).thenThrow(EntityNotFoundException.class);

    //     Assertions.assertThrows(EntityNotFoundException.class, () -> {
    //         underTest.getProductById(expectedId);
    //     });
    // }
}
