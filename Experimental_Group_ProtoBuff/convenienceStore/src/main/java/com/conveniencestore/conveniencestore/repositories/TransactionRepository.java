package com.conveniencestore.conveniencestore.repositories;

import com.conveniencestore.conveniencestore.domain.Transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Optional<Transaction> findByProductId(int id);
}
