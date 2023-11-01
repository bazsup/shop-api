package com.sit.shopping.product.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "products")
public class Product {

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "imageUrl", nullable = false)
	private String imageUrl;

	@Column(name = "category", nullable = false)
	private Category category;

	public Product() {
	}

	public static Product create(String name, double price, String imageUrl, Category category) {
		Product product = new Product();
		product.id = UUID.randomUUID().toString();
		product.name = name;
		product.price = price;
		product.imageUrl = imageUrl;
		product.category = category;
		return product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
