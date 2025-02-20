package com.isep.acme.repositories.h2Repos.Implementation;

import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.repositories.ProductServiceRepo;
import com.isep.acme.repositories.h2Repos.Repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class H2ProductRepoImpl implements ProductServiceRepo {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findByDesignation(String designation) {
        return repository.findByDesignation(designation);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return repository.findBySku(sku);
    }

    @Override
    public List<Product> getCatalog() {
        return repository.getCatalog();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }

    @Override
    public Integer updateBySku(String sku,Product product) {
        return repository.update(sku,product.getDesignation(),product.getDescription(),product.isPublished());
    }
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Optional<Product> findById(Long productID) {
        return repository.findById(productID);
    }

}

