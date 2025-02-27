package com.isep.acme.services;

import com.isep.acme.Dto.CreateProductDTO;
import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.Dto.ProductDetailDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.protobuf.CreateProductDTOOuterClass;
import com.isep.acme.protobuf.ProductDTOOuterClass;

import java.util.Optional;

public interface ProductService {

    Optional<ProductDTOOuterClass.ProductDTO> findBySku(final String sku);

    Optional<Product> getProductBySku(final String sku);

     ProductDTOOuterClass.ProductCatalogDTO findByDesignation(final String designation);

    ProductDTOOuterClass.ProductCatalogDTO  getCatalog();

    ProductDetailDTO getDetails(final String sku);

    ProductDTOOuterClass.ProductDTO create(final CreateProductDTOOuterClass.CreateProductDTO manager);

    ProductDTOOuterClass.ProductDTO updateBySku(final String sku, final Product product);

    void deleteBySku(final String sku);

}
