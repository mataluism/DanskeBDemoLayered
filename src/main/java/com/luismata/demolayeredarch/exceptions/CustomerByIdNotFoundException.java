package com.luismata.demolayeredarch.exceptions;

public class CustomerByIdNotFoundException extends Exception {
    public CustomerByIdNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
