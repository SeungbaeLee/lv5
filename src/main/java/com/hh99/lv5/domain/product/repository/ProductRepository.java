package com.hh99.lv5.domain.product.repository;

import com.hh99.lv5.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
