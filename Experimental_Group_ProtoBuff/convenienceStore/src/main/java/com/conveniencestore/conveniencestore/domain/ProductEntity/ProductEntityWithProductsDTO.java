package com.conveniencestore.conveniencestore.domain.ProductEntity;

import com.conveniencestore.conveniencestore.domain.Products.Product;

import java.util.List;

public record ProductEntityWithProductsDTO(
        ProductEntity productEntity,
        List<Product> products
) {
}
