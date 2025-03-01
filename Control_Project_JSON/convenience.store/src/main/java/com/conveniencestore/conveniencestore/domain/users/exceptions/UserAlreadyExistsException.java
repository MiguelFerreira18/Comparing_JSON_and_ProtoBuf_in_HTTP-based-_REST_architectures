package com.conveniencestore.conveniencestore.domain.users.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("A user with the same email already exists.");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

