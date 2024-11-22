package com.example.alomshop.dto;

import com.example.alomshop.entity.Product;
import com.example.alomshop.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class CartDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;  // `User`와의 관계를 설정

    @Column(name = "user_id")
    private Long userId;  // 실제 외래키로 사용할 필드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product; // Product와의 관계 설정

    @Column(name = "product_id")
    private Long productId;  // 실제 외래키로 사용할 필드

    private int quantity;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public CartDTO(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.userId = user.getId();
        this.productId = product.getId();
    }

    public CartDTO() {
    }
}
