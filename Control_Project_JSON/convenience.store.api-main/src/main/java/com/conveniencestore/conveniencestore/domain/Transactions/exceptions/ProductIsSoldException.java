package com.conveniencestore.conveniencestore.domain.Transactions.exceptions;

public class ProductIsSoldException extends  RuntimeException{
    public ProductIsSoldException() {
        super("This product has already been sold.");
    }
    public ProductIsSoldException(String message) {
        super(message);
    }
}
