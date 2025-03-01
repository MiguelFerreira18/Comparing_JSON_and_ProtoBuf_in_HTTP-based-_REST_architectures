package com.conveniencestore.conveniencestore.domain.Products.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("This product was not found.");
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
