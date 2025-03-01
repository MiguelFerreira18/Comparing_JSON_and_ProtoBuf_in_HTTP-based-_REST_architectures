package com.conveniencestore.conveniencestore.domain.users.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("This user was not found.");
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
