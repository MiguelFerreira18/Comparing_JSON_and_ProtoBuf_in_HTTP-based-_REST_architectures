package com.conveniencestore.conveniencestore.domain.ProductEntity;

import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityDto;
import com.conveniencestore.conveniencestore.protobuf.ProductEntityOuterClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ProductEntity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "entityId", fetch = FetchType.EAGER)
    List<Product> products;

    public ProductEntity(ProductEntityDto.ProductEntityDTO request) {
        this.name = request.getName();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
