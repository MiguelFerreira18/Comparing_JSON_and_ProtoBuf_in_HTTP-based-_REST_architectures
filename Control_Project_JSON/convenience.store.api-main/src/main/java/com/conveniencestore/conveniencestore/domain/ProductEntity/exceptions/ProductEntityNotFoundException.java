package com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions;

public class ProductEntityNotFoundException extends  RuntimeException{
    public ProductEntityNotFoundException() {
        super("Product entity not found.");
    }
    public ProductEntityNotFoundException(String message) {
        super(message);
    }
}
