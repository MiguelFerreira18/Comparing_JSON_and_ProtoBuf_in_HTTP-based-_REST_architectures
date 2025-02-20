package com.isep.acme.controllers;

import com.isep.acme.Dto.ProductDTO;
import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;
    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;


    @Operation(summary = "gets catalog, i.e. all products")
    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getCatalog() {
        final var products = service.getCatalog();
        List<ProductDTO> pDto = new ArrayList();
        for (ProductDTO pd : products) {
            pDto.add(pd);
        }
        return ResponseEntity.ok().body(pDto);
    }
    @GetMapping
    @RequestMapping("/rabbitmq")
    public ResponseEntity<String> getRabbitmqHost() {
        return ResponseEntity.ok().body(rabbitmqHost);
    }

    @Operation(summary = "finds product by sku")
    @GetMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable("sku") final String sku) {

        final Optional<ProductDTO> product = Optional.ofNullable(service.findBySku(sku).get());

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else
            return ResponseEntity.ok().body(product.get());
    }

    @Operation(summary = "finds product by designation")
    @GetMapping(value = "/designation/{designation}")
    public ResponseEntity<Iterable<ProductDTO>> findAllByDesignation(@PathVariable("designation") final String designation) {
        List<ProductDTO> p = (List<ProductDTO>) service.findByDesignation(designation);
        return ResponseEntity.ok().body(p);
    }
}