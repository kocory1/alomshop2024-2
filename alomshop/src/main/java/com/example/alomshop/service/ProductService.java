package com.example.alomshop.service;

import com.example.alomshop.domain.Product;
import com.example.alomshop.domain.User;
import com.example.alomshop.repository.ProductRepository;
import com.example.alomshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
