package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.Products.exceptions.ProductNotFoundException;
import com.conveniencestore.conveniencestore.domain.Transactions.Transaction;
import com.conveniencestore.conveniencestore.domain.Transactions.TransactionDTO;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.ProductIsSoldException;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.TransactionNotFoundException;
import com.conveniencestore.conveniencestore.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @GetMapping
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(required = false, defaultValue = "asc")
            String order
    ) {
        if (!order.equals("asc") && !order.equals("desc")) {
            ErrorDTO error = new ErrorDTO("Request param is not valid.", 404);
            return ResponseEntity.status(400).body(error);
        }
        return ResponseEntity.ok().body(this.service.getAll("id", order));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Integer id) {
        Transaction transaction = this.service.getById(id);
        return ResponseEntity.ok().body(transaction);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getTransactionByProductId(@PathVariable Integer id) {
        Transaction transaction = this.service.getByProductId(id);
        if (transaction == null) {
            ErrorDTO error = new ErrorDTO("This transaction was not found.", 404);
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(transaction);
    }

    @PostMapping
    public ResponseEntity<?> registerNewTransaction(@RequestBody @Valid TransactionDTO data) {
        Transaction transaction = this.service.insert(data);
        return ResponseEntity.status(201).body(transaction);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Integer id) {
        Transaction transaction = this.service.delete(id);
        return ResponseEntity.ok().body(transaction);
    }
}
