package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.domain.Products.ProductDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.createProductEntity();
    }

    @Test
    @DisplayName("Should get a sold product in the database.")
    void findBySolCase1() {
        this.createProduct(new ProductDTO(1), true);
        List<Product> products = this.productRepository.findBySoldTrue(Sort.by(Sort.Direction.ASC,"id"));
        assertThat(products.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Should be able to get not sold products from the database.")
    void findBySolCase2() {
        this.createProduct(new ProductDTO(1), false);
        List<Product> products = this.productRepository.findBySoldFalse(Sort.by(Sort.Direction.ASC, "id"));
        assertThat(products.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Should not be able to get product from database.")
    void findBySolCase3() {
        this.createProduct(new ProductDTO(1), true);
        List<Product> products = this.productRepository.findBySoldFalse(Sort.by(Sort.Direction.ASC, "id"));
        assertThat(products.isEmpty()).isTrue();
    }
    @Test
    @DisplayName("Should not be able to get product from database.")
    void findBySolCase4() {
        this.createProduct(new ProductDTO(1), false);
        List<Product> products = this.productRepository.findBySoldTrue(Sort.by(Sort.Direction.ASC, "id"));
        assertThat(products.isEmpty()).isTrue();
    }

    private void createProductEntity(){
        ProductEntity productEntity = new ProductEntity(new ProductEntityDTO("pd"));
        this.entityManager.persist(productEntity);
    }

    private void createProduct(ProductDTO data, boolean sold){
        Product product = new Product(data);
        product.setSold(sold);
        this.entityManager.persist(product);
    }
}