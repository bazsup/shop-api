package com.sit.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.shopping.cart.model.Cart;

public interface CartRepositoryDB extends JpaRepository<Cart, String>, CartRepository {

}
