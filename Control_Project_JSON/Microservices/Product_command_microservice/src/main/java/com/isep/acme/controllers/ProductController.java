package com.isep.acme.controllers;

import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@RequestMapping("/products")
class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @Operation(summary = "creates a product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody Product manager) {
        System.out.println("ProductControllerh2.create");
        try {
            final ProductDTO product = service.create(manager);
            return new ResponseEntity<ProductDTO>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product must have a unique SKU.");
        }
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity<Integer> Update(@PathVariable("sku") final String sku, @RequestBody final Product product) {

        final Integer productDTO = service.updateBySku(sku, product);

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

    @Operation(summary = "adds approval to a product")
    @PatchMapping(value = "/approval/{sku}")
    public ResponseEntity<ProductDTO> addApproval(@PathVariable("sku") final String sku) {
        service.addApproval(sku);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "publishes a product")
    @PatchMapping(value = "/publish/{sku}")
    public ResponseEntity<Boolean> publish(@PathVariable("sku") final String sku) {
        return ResponseEntity.ok().body(service.publish(sku));
    }
}