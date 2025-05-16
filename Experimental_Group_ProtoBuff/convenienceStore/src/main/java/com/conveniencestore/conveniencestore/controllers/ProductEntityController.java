package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityDto;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityOuterClass;
import com.conveniencestore.conveniencestore.services.ProductEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Get all products",
            description = "Get all products",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Products found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request param is not valid",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
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
    @Operation(
            summary = "Get product by id",
            description = "Get product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ProductEntity.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Product not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntityOuterClass.ProductEntity productEntity = this.service.getById(id);
        return ResponseEntity.ok().body(productEntity);
    }

    @PostMapping(produces = "application/x-protobuf", consumes = "application/x-protobuf")
    @Operation(
            summary = "Register a new product",
            description = "Register a new product",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ProductEntity.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Product not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> registerNewProduct(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Product data",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductEntityDTO.class)
            )
    ) @RequestBody @Valid ProductEntityDto.ProductEntityDTO request) {
        ProductEntityOuterClass.ProductEntity productEntity = this.service.insert(request);
        return ResponseEntity.status(201).body(productEntity);
    }

    @PutMapping(value = "{id}", produces = "application/x-protobuf", consumes = "application/x-protobuf")
    @Operation(
            summary = "Edit a product",
            description = "Edit a product",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product edited",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ProductEntity.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Product not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Product data",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductEntityDTO.class)
            )
    ) @RequestBody @Valid ProductEntityDto.ProductEntityDTO productEntityRecord) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntityOuterClass.ProductEntity productEntity = this.service.update(id, productEntityRecord);
        return ResponseEntity.ok().body(productEntity);
    }

    @DeleteMapping(value = "{id}", produces = "application/x-protobuf")
    @Operation(
            summary = "Delete a product",
            description = "Delete a product",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product deleted",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ProductEntity.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Product not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        ProductEntityOuterClass.ProductEntity productEntity = this.service.delete(id);
        return ResponseEntity.ok().body(productEntity);
    }
}
