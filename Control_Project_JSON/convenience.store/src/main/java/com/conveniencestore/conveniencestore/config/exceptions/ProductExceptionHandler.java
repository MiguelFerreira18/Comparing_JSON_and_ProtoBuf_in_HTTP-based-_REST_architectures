package com.conveniencestore.conveniencestore.config.exceptions;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.ProductEntity.exceptions.ProductEntityNotFoundException;
import com.conveniencestore.conveniencestore.domain.Products.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ErrorDTO> hanndler(ProductNotFoundException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 404);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler({ProductEntityNotFoundException.class})
    public ResponseEntity<ErrorDTO> hanndler(ProductEntityNotFoundException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 404);
        return ResponseEntity.status(error.status()).body(error);
    }
}
