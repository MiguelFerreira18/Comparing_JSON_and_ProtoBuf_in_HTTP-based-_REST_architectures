package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityDto;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityOuterClass;
import com.conveniencestore.conveniencestore.services.ProductEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("products/entities")
public class ProductEntityController {
    private final ProductEntityService service;
    private static final List<String> VALID_SEARCH_PARAMETERS = List.of("id", "name", "asc", "desc");


    //! to proto
    @GetMapping(produces = "application/x-protobuf")
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false, defaultValue = "id")
            String orderby,
            @RequestParam(required = false, defaultValue = "asc")
            String order
    ) {
        if (VALID_SEARCH_PARAMETERS.contains(orderby) && VALID_SEARCH_PARAMETERS.contains(order))
            return ResponseEntity.ok().body(ProductEntityOuterClass.ProductEntityCatalog.newBuilder().addAllProducts(this.service.getAll(orderby, order)).build());
        ErrorDTO error = new ErrorDTO("Request param is not valid.", 400);
        return ResponseEntity.status(400).body(error);
    }

    @GetMapping(value = "{id}", produces = "application/x-protobuf")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntityOuterClass.ProductEntity productEntity = this.service.getById(id);
        return ResponseEntity.ok().body(productEntity);
    }

    @PostMapping(produces = "application/x-protobuf", consumes = "application/x-protobuf")
    public ResponseEntity<?> registerNewProduct(@RequestBody @Valid ProductEntityDto.ProductEntityDTO request) {
        ProductEntityOuterClass.ProductEntity productEntity = this.service.insert(request);
        return ResponseEntity.status(201).body(productEntity);
    }

    @PutMapping(value = "{id}", produces = "application/x-protobuf", consumes = "application/x-protobuf")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductEntityDto.ProductEntityDTO productEntityRecord) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntityOuterClass.ProductEntity productEntity = this.service.update(id, productEntityRecord);
        return ResponseEntity.ok().body(productEntity);
    }

    @DeleteMapping(value = "{id}", produces = "application/x-protobuf")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntityOuterClass.ProductEntity productEntity = this.service.delete(id);
        return ResponseEntity.ok().body(productEntity);
    }
}
