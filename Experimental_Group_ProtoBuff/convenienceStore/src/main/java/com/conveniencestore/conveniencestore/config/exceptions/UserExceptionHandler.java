package com.conveniencestore.conveniencestore.config.exceptions;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserAlreadyExistsException;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorDTO> hanndler(UserNotFoundException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 404);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<ErrorDTO> hanndler(UserAlreadyExistsException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 409);
        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorDTO> hanndler(BadCredentialsException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 401);
        return ResponseEntity.status(error.status()).body(error);
    }
    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ErrorDTO> hanndler(UsernameNotFoundException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), 404);
        return ResponseEntity.status(error.status()).body(error);
    }
}
