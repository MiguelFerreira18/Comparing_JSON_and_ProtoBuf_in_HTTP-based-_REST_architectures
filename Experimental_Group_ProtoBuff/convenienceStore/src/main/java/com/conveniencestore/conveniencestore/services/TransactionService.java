package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.Products.Product;
import com.conveniencestore.conveniencestore.domain.Products.exceptions.ProductNotFoundException;
import com.conveniencestore.conveniencestore.domain.Transactions.Transaction;
import com.conveniencestore.conveniencestore.domain.Transactions.TransactionDTO;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.ProductIsSoldException;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.TransactionNotFoundException;
import com.conveniencestore.conveniencestore.repositories.ProductRepository;
import com.conveniencestore.conveniencestore.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService implements ServiceInterface<Transaction, TransactionDTO> {
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public Transaction insert(TransactionDTO data) {
        Product product = this.productRepository.findById(data.productId()).orElseThrow(ProductNotFoundException::new);
        if(product.isSold()) throw new ProductIsSoldException();
        product.setSold(true);
        product.setUpdatedAt(LocalDateTime.now());
        Transaction transaction = new Transaction(data);
        productRepository.save(product);
        transaction = this.transactionRepository.save(transaction);
        return transaction;
    }

    public List<Transaction> getAll(String orderby, String order) {
        if (!(orderby.equals("id"))) orderby = "id";
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
        return this.transactionRepository.findAll(Sort.by(direction, orderby));
    }

    public Transaction getById(int id) {
        return this.transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
    }

    public Transaction getByProductId(int id) {
        return this.transactionRepository.findByProductId(id).orElseThrow(TransactionNotFoundException::new);
    }

    public Transaction delete(int id) {
        Transaction transaction = this.transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        this.transactionRepository.delete(transaction);
        return transaction;
    }

    public Transaction update(int id, TransactionDTO data) {
        return null;
    }
}
