package com.conveniencestore.conveniencestore.domain.Transactions;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cpf;

    private int productId;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Transaction(TransactionDTO data){
        this.cpf = data.cpf();
        this.productId = data.productId();
        this.createdAt = LocalDateTime.now();
    }
}
