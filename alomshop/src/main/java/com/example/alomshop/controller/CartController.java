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

import java.util.HashMap;
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
    public ResponseEntity<String> addCart(@RequestBody Map<String, Object> request) {
        Long userId = ((Number) request.get("userId")).longValue();
        Long productId = ((Number) request.get("productId")).longValue();
        int quantity = ((Number) request.get("quantity")).intValue();
        Cart cart = cartService.createCart(userId, productId, quantity);
        return ResponseEntity.ok("Added cart successfully");
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
    public ResponseEntity<Map<String, Object>> deleteCarts(@RequestBody Map<String, List<Long>> request) {
        List<Long> cartIds = request.get("cartIds");

        // cartIds가 null이거나 빈 리스트인지 확인
        if (cartIds == null || cartIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "삭제할 Cart ID가 없습니다."));
        }

        // Cart 삭제
        cartRepository.deleteAllByIdInBatch(cartIds);

        // 남은 Cart 목록 조회
        List<Cart> remainingCarts = cartRepository.findAll();

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("message", "선택한 Cart가 삭제되었습니다.");
        response.put("remainingCarts", remainingCarts);
        return ResponseEntity.ok(response);
    }
}
