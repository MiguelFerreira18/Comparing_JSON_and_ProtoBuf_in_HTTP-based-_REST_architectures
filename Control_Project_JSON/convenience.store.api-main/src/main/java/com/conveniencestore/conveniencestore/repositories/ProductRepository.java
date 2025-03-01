package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.Products.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findBySoldTrue(Sort sort);
    List<Product> findBySoldFalse(Sort sort);
    List<Product> findByEntityId(Integer id);
}
