package com.sit.shopping.cart.repository;

import com.sit.shopping.cart.model.Cart;
import com.sit.shopping.exception.EntityNotFoundException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ConditionalOnProperty(name = "database.inmemory.enabled", havingValue = "true")
public class CartRepositoryInMem implements CartRepository {
    private ConcurrentHashMap<String, Cart> cartMap;

    public CartRepositoryInMem() {
        if (cartMap == null) {
            cartMap = new ConcurrentHashMap<>();
        }
        cartMap.putIfAbsent("ce0b9fbe-7ad8-11eb-9439-0242ac130002",
                Cart.create("ce0b9fbe-7ad8-11eb-9439-0242ac130002"));
    }

    @Override
    public Optional<Cart> findById(String cartId) {
        if (cartId == null) {
            throw new EntityNotFoundException("A cart cannot be found");
        }

        Cart cart = cartMap.get(cartId);

        if (cart == null) {
            throw new EntityNotFoundException("A cart cannot be found");
        }

        return Optional.of(cart);
    }

    @Override
    public <S extends Cart> S save(S cart) {
        if (cart == null || cart.getId() == null) {
            throw new EntityNotFoundException("A cart cannot be found");
        }

        cartMap.put(cart.getId(), cart);
        return cart;
    }

}
