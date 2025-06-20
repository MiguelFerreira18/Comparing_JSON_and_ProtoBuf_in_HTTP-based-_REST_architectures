package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityWithProductsDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.protobuf.LocalDateTimePbOuterClass;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityDto;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityOuterClass;
import com.conveniencestore.conveniencestore.protobuf.ProductOuterClass;
import com.conveniencestore.conveniencestore.repositories.ProductEntityRepository;
import com.conveniencestore.conveniencestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductEntityService implements ServiceInterface<ProductEntityOuterClass.ProductEntity, ProductEntityDto.ProductEntityDTO> {
    private final ProductEntityRepository productEntityRepository;
    private final ProductRepository productRepository;

    public ProductEntityOuterClass.ProductEntity insert(ProductEntityDto.ProductEntityDTO request) {
        ProductEntity productEntity = new ProductEntity(request);
        productEntity = this.productEntityRepository.save(productEntity);
        return ProductEntityOuterClass.ProductEntity.newBuilder()
                .setId(productEntity.getId())
                .setName(productEntity.getName())
                .setCreatedAt(convertLocalDateTime(productEntity.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(productEntity.getUpdatedAt()))
                .build();
    }

    public ProductEntityOuterClass.ProductEntity delete(int id) {
        ProductEntity productEntity = this.productEntityRepository.findById(id).orElseThrow(ProductEntityNotFoundException::new);
        this.productEntityRepository.delete(productEntity);
        return ProductEntityOuterClass.ProductEntity.newBuilder()
                .setId(productEntity.getId())
                .setName(productEntity.getName())
                .setCreatedAt(convertLocalDateTime(productEntity.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(productEntity.getUpdatedAt()))
                .build();
    }

    public ProductEntityOuterClass.ProductEntity update(int id, ProductEntityDto.ProductEntityDTO productEntityRecord) {
        ProductEntity productEntity = this.productEntityRepository.findById(id).orElseThrow(ProductEntityNotFoundException::new);
        productEntity.setName(productEntityRecord.getName());
        productEntity.setUpdatedAt(LocalDateTime.now());
        productEntity = this.productEntityRepository.save(productEntity);
        return ProductEntityOuterClass.ProductEntity.newBuilder()
                .setId(productEntity.getId())
                .setName(productEntity.getName())
                .setCreatedAt(convertLocalDateTime(productEntity.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(productEntity.getUpdatedAt()))
                .build();
    }

    public List<ProductEntityOuterClass.ProductEntity> getAll(String orderby, String order) {
        Sort.Direction direction;
        switch (order) {
            case "asc" -> {
                direction = Sort.Direction.ASC;
            }
            case "desc" -> {
                direction = Sort.Direction.DESC;
            }
            default -> {
                direction = Sort.DEFAULT_DIRECTION;
            }
        }
        return productEntityRepository.findAll(Sort.by(direction, orderby)).stream().map(this::convertFromProductEntity).toList();
    }

    public ProductEntityOuterClass.ProductEntity getById(int id) {
        ProductEntity productEntity = this.productEntityRepository.findById(id).orElseThrow(ProductEntityNotFoundException::new);
        return ProductEntityOuterClass.ProductEntity.newBuilder()
                .setId(productEntity.getId())
                .setName(productEntity.getName())
                .setCreatedAt(convertLocalDateTime(productEntity.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(productEntity.getUpdatedAt()))
                .build();

    }

    public List<ProductEntityWithProductsDTO> getProductEntitiesWithProducts() {
        List<ProductEntity> productEntities = this.productEntityRepository.findAll();
        List<Product> products = this.productRepository.findAll();
        List<ProductEntityWithProductsDTO> response = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            List<Product> p = products
                    .stream()
                    .filter(product -> product.getEntityId() == productEntity.getId())
                    .collect(Collectors.toList());
            ProductEntityWithProductsDTO productEntityWithProductsDTO = new ProductEntityWithProductsDTO(productEntity, p);
            response.add(productEntityWithProductsDTO);
        }
        return response;
    }


    private ProductEntityOuterClass.ProductEntity convertFromProductEntity(ProductEntity productEntity) {
        ProductEntityOuterClass.ProductEntity.Builder builder = ProductEntityOuterClass.ProductEntity.newBuilder()
                .setId(productEntity.getId())
                .setName(productEntity.getName())
                .setCreatedAt(convertLocalDateTime(productEntity.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(productEntity.getUpdatedAt()));

        if (productEntity.getProducts() != null) {
            for (Product product : productEntity.getProducts()) {
                builder.addProducts(convertFromProduct(product));
            }
        }


        return builder.build();
    }

    private ProductOuterClass.Product convertFromProduct(Product product) {
        return ProductOuterClass.Product.newBuilder()
                .setId(product.getId())
                .setEntityId(product.getEntityId())
                .setSold(product.isSold())
                .setCreatedAt(convertLocalDateTime(product.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(product.getUpdatedAt()))
                .build();
    }


    private LocalDateTimePbOuterClass.LocalDateTimePb convertLocalDateTime(LocalDateTime time) {
        return LocalDateTimePbOuterClass.LocalDateTimePb.newBuilder()
                .setYear(time.getYear())
                .setMonth(time.getMonthValue())
                .setDay(time.getDayOfMonth())
                .build();
    }
}
