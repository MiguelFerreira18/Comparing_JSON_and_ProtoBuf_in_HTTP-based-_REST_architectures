package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntity;
import com.conveniencestore.conveniencestore.domain.ProductEntity.ProductEntityDTO;
import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.domain.Products.ProductDTO;
import com.conveniencestore.conveniencestore.domain.Transactions.Transaction;
import com.conveniencestore.conveniencestore.domain.Transactions.TransactionDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TransactionRepositoryTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp(){
        this.createProduct();
        this.createProductEntity();
        this.createNewTransaction();
    }

    @Test
    @DisplayName("Should be able to get transaction from database.")
    void findByProductIdCase1() {
        Optional<Transaction> transaction = this.transactionRepository.findByProductId(1);
        assertThat(transaction.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not be able to get transaction from database.")
    void findByProductIdCase2() {
        Optional<Transaction> transaction = this.transactionRepository.findByProductId(20);
        assertThat(transaction.isPresent()).isFalse();
    }
    private void createProductEntity(){
        ProductEntity productEntity = new ProductEntity(new ProductEntityDTO("pd"));
        this.entityManager.persist(productEntity);
    }
    private void createProduct(){
        Product product = new Product(new ProductDTO(1));
        this.entityManager.persist(product);
    }
    private void createNewTransaction(){
        Transaction transaction = new Transaction(new TransactionDTO("111.111.11", 1));
        this.entityManager.persist(transaction);
    }
}