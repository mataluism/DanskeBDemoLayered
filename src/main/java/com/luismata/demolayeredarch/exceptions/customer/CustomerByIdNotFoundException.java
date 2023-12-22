package com.luismata.demolayeredarch.exceptions.customer;

public class CustomerByIdNotFoundException extends Exception {
    public CustomerByIdNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
