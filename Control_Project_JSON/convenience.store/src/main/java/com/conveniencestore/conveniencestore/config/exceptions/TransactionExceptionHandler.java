package com.conveniencestore.conveniencestore.config.exceptions;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.ProductIsSoldException;
import com.conveniencestore.conveniencestore.domain.Transactions.exceptions.TransactionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler {
    @ExceptionHandler({ProductIsSoldException.class})
    public ResponseEntity<ErrorDTO> hanndler(ProductIsSoldException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 403);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler({TransactionNotFoundException.class})
    public ResponseEntity<ErrorDTO> hanndler(TransactionNotFoundException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 404);
        return ResponseEntity.status(error.status()).body(error);
    }
}
