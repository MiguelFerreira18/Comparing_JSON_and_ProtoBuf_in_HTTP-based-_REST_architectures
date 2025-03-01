package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
}
