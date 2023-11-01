package com.sit.shopping.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.sit.shopping.handler.RestTemplateResponseErrorHandler;
import com.sit.shopping.product.model.Product;

@Component
public class ProductClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productClient.baseUrl}")
    private String baseUrl;

    public Product getProduct(@PathVariable("id") String productId){
        LOGGER.info("Fetching Product using Product Id {}", productId);
        String url = String.format("%s/product/%s", baseUrl, productId);
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        ResponseEntity<Product> productResponse = restTemplate.getForEntity(url, Product.class);

        return productResponse.getBody();
    }
}
