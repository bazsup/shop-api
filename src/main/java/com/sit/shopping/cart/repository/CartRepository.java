package com.sit.shopping.cart.repository;

import java.util.Optional;

import com.sit.shopping.cart.model.Cart;

public interface CartRepository {

    Optional<Cart> findById(String cartId);

    <S extends Cart> S save(S entity);
}
