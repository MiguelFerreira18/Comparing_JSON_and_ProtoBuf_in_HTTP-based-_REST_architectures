package com.conveniencestore.conveniencestore.domain.Transactions.exceptions;

public class TransactionNotFoundException extends  RuntimeException{
    public TransactionNotFoundException() {
        super("Transaction not found.");
    }
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
