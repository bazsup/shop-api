package com.sit.shopping.cart.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sit.shopping.cart.repository.CartRepository;
import com.sit.shopping.client.ProductClient;
import com.sit.shopping.coupon.model.Coupon;
import com.sit.shopping.coupon.repository.CouponRepository;
import com.sit.shopping.product.model.Product;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sit.shopping.cart.dto.AddProductRequest;
import com.sit.shopping.cart.dto.AddProductResponse;
import com.sit.shopping.cart.dto.ApplyCouponRequest;
import com.sit.shopping.cart.dto.ApplyCouponResponse;
import com.sit.shopping.cart.dto.CartStatusDTO;
import com.sit.shopping.cart.model.Cart;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@Validated
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CouponRepository couponRepository;

    @PostMapping("/add")
    @Transactional
    public AddProductResponse addProductToCart(@RequestBody @Valid AddProductRequest request) {
        Product product = productClient.getProduct(request.getProductId());

        Cart cart = findOrCreate(request.getCartId());

        cart.addProduct(product);

        cartRepository.save(cart);

        return new AddProductResponse(cart);
    }

    @GetMapping("/status")
    public CartStatusDTO getCartStatus(@RequestParam @Valid @NotBlank String cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);

        return new CartStatusDTO(cart.get());
    }

    @GetMapping("/summary")
    public Optional<Cart> getCartSummary(@RequestParam String cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);

        return cart;
    }

    @PostMapping("/applyCoupon")
    @Transactional
    public ApplyCouponResponse applyCoupon(@RequestBody @Valid ApplyCouponRequest request) {
        Cart cart = this.findOrCreate(request.getCartId());

        Coupon coupon = couponRepository.findByCoupon(request.getCoupon());

        coupon.applyToCart(cart);

        cartRepository.save(cart);

        return new ApplyCouponResponse(cart.getDiscountDescription());
    }

    private Cart findOrCreate(String cartId) {
        Optional<Cart> maybe = cartRepository.findById(cartId);
        Cart cart;
        if (maybe.isPresent()) {
            cart = maybe.get();
        } else {
            cart = Cart.create(cartId);
        }
        return cart;
    }
}
