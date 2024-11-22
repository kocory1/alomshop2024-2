package com.example.alomshop.service;

import com.example.alomshop.domain.Cart;
import com.example.alomshop.domain.Product;
import com.example.alomshop.domain.User;
import com.example.alomshop.dto.CartDTO;
import com.example.alomshop.repository.CartRepository;
import com.example.alomshop.repository.ProductRepository;
import com.example.alomshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart createCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartDTO cartDTO = new CartDTO(user, product, quantity);
        Cart cart = new Cart(cartDTO.getUserId(),cartDTO.getProductId(),cartDTO.getQuantity());
        return cartRepository.save(cart);
    }

    public List<Cart> getCartsByUserIdAndProductId(Long userId, Long productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }
}
