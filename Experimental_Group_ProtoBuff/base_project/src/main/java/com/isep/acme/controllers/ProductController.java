package com.isep.acme.controllers;

import com.isep.acme.Dto.CreateProductDTO;
import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.protobuf.CreateProductDTOOuterClass;
import com.isep.acme.protobuf.ProductDTOOuterClass;
import com.isep.acme.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    private ProductService service;


    @Operation(summary = "gets catalog, i.e. all products")
    @GetMapping(produces = "application/x-protobuf")
    public ResponseEntity<ProductDTOOuterClass.ProductCatalogDTO> getCatalog() { //! to protobufs
        return ResponseEntity.ok().body( service.getCatalog());
    }

    @Operation(summary = "finds product by sku")
    @GetMapping(value = "/{sku}", produces = "application/x-protobuf")
    public ResponseEntity<ProductDTOOuterClass.ProductDTO> getProductBySku(@PathVariable("sku") final String sku) { //! to protobufs

        final Optional<ProductDTOOuterClass.ProductDTO> product = Optional.ofNullable(service.findBySku(sku).get());

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else
            return ResponseEntity.ok().body(product.get());
    }

    @Operation(summary = "finds product by designation")
    @GetMapping(value = "/designation/{designation}")
    public ResponseEntity<ProductDTOOuterClass.ProductCatalogDTO> findAllByDesignation(@PathVariable("designation") final String designation) {
        return ResponseEntity.ok().body(service.findByDesignation(designation));
    }

    @Operation(summary = "creates a product")
    @PostMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTOOuterClass.ProductDTO> create(@RequestBody CreateProductDTOOuterClass.CreateProductDTO manager) { //! to protobufs
        try {
            final ProductDTOOuterClass.ProductDTO product = service.create(manager);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product must have a unique SKU.");
        }
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity<ProductDTOOuterClass.ProductDTO> Update(@PathVariable("sku") final String sku, @RequestBody final Product product) {

        final ProductDTOOuterClass.ProductDTO productDTO = service.updateBySku(sku, product);

        if (productDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else
            return ResponseEntity.ok().body(productDTO);
    }

    @Operation(summary = "deletes a product")
    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<Product> delete(@PathVariable("sku") final String sku) {

        service.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }
}