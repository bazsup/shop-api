package com.sit.shopping.product.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.sit.shopping.product.controller.ProductController;
import com.sit.shopping.product.model.Category;
import com.sit.shopping.product.model.Product;

@Repository
@ConditionalOnProperty(name = "in-memory-database.enabled", havingValue = "true")
public class ProductRepositoryInMem implements ProductRepository, InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	private final List<Product> products = new ArrayList<>();

	public ProductRepositoryInMem() {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.products.add(Product.create("Loose Cropped Jeans (Damaged)", 42.57,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/448429/sub/goods_448429_sub14"
						+ ".jpg?width=1600&impolicy=quality_75", Category.WONMEN));
		this.products.add(Product.create("Smart Skort Solid", 140.37,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/455844/sub/goods_455844_sub14"
						+ ".jpg?width=1600&impolicy=quality_75", Category.WONMEN));
		this.products.add(Product.create("Smart Tucked Shorts", 22.57,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/453555/sub/goods_453555_sub14"
						+ ".jpg?width=1600&impolicy=quality_75", Category.WONMEN));
		this.products.add(Product.create("Printed Cotton Square Neck Short Sleeve Mini Dress", 28.28,
				"https://image.uniqlo.com/UQ/ST3/th/imagesgoods/449183/item/thgoods_01_449183"
						+ ".jpg?width=1600&impolicy=quality_75", Category.WONMEN));
		this.products.add(Product.create("Printed Button Down Camisole Flare Dress", 42.57,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/455793/sub/goods_455793_sub14"
						+ ".jpg?width=1600&impolicy=quality_75", Category.WONMEN));
		this.products.add(Product.create("Smooth Cotton Tiered Sleeveless Dress", 22.57,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/452896/sub/goods_452896_sub14"
						+ ".jpg?width=1600&impolicy=quality_75", Category.WONMEN));
		this.products.add(Product.create("Soft Twill Stands Collar Long Sleeve Shirt", 28.28,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/450261/item/goods_56_450261"
						+ ".jpg?width=1600&impolicy=quality_75", Category.MEN));
		this.products.add(Product.create("Oxford Striped Slim Fit Long Sleeve Shirt", 28.28,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/452300/sub/goods_452300_sub14"
						+ ".jpg?width=1600&impolicy=quality_75", Category.MEN));
		this.products.add(Product.create("Dry Pique Wide Horizontal Stripes Short Sleeve Polo", 22.57,
				"https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/455676/sub/goods_455676_sub14"
						+ ".jpg?width=1600&impolicy=quality_75", Category.MEN));

	}

	@Override
	public Optional<Product> findById(String productId) {
		LOGGER.info("Getting Product from Product Repo with Product Id {}", productId);
		return this.products.stream().filter(product -> product.getId().equals(productId)).findFirst();
	}

	@Override
	public List<Product> findAll() {
		return this.products;
	}

	public void addProduct(Product p) {
		this.products.add(p);
	}

	@Override
	public List<Product> findAllByCategory(Category category) {
		List<Product> filteredProducts = new ArrayList<>();
		for (Product product : this.products) {
			if (product.getCategory().equals(category)) {
				filteredProducts.add(product);
			}
		}
		return filteredProducts;
	}
}
