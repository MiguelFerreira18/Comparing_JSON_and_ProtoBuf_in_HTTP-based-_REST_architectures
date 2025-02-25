package com.isep.acme.services;

import com.isep.acme.Dto.CreateProductDTO;
import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.Dto.ProductDetailDTO;
import com.isep.acme.model.H2Entity.Product;

import java.util.Optional;

public interface ProductService {

    Optional<ProductDTO> findBySku(final String sku);

    Optional<Product> getProductBySku(final String sku);

    Iterable<ProductDTO> findByDesignation(final String designation);

    Iterable<ProductDTO> getCatalog();

    ProductDetailDTO getDetails(final String sku);

    ProductDTO create(final CreateProductDTO manager);

    ProductDTO updateBySku(final String sku, final Product product);

    void deleteBySku(final String sku);

}
