package com.luismata.demolayeredarch.exceptions;

public class CustomerByIdNotFound extends Exception {
    public CustomerByIdNotFound(String errorMessage) {
        super(errorMessage);
    }
}
