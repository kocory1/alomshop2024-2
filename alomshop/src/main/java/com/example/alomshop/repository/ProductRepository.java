package com.example.alomshop.repository;

import com.example.alomshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//임시
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
