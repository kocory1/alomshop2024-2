package com.example.alomshop.controller;

import com.example.alomshop.domain.Cart;
import com.example.alomshop.repository.CartRepository;
import com.example.alomshop.repository.ProductRepository;
import com.example.alomshop.repository.UserRepository;
import com.example.alomshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("userId").toString());
        Long productId = Long.valueOf(payload.get("productId").toString());
        int quantity = Integer.parseInt(payload.get("quantity").toString());

        Cart createdCart = cartService.createCart(userId, productId, quantity);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getCartsByUserIdAndProductId(
            @RequestParam("user_id") Long userId,
            @RequestParam("product_id") Long productId) {
        List<Cart> carts = cartService.getCartsByUserIdAndProductId(userId, productId);
        if (carts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carts);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCarts(@RequestBody Map<String, List<Long>> request) {
        List<Long> cartIds = request.get("cartIds");

        // cartIds가 null이거나 빈 리스트인지 확인
        if (cartIds == null || cartIds.isEmpty()) {
            return ResponseEntity.badRequest().build();  // 요청이 잘못된 경우 400 Bad Request 반환
        }

        cartRepository.deleteAllByIdInBatch(cartIds);
        return ResponseEntity.noContent().build();
    }
}
