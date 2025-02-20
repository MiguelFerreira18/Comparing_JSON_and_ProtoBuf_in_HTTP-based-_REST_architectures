package com.isep.acme.repositories;

import com.isep.acme.model.H2Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceRepo {
    List<Product> findByDesignation(String designation);
    Optional<Product> findBySku(String sku);
    Iterable<Product> getCatalog();
    Product save(Product product);
    void deleteBySku(String sku);
    Integer updateBySku(String sku,Product productToChange);
    List<Product> findAll();
    Optional<Product> findById(Long productID);


}
