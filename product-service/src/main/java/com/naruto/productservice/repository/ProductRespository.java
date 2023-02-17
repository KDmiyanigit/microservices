package com.naruto.productservice.repository;

import com.naruto.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository<Product, Long> {
}
