package com.clicknbuy.clicknbuyecommerce.repositories;

import com.clicknbuy.clicknbuyecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
