package com.luismata.demolayeredarch.exceptions.account;

public class AccountByIdNotFoundException extends Exception {
    public AccountByIdNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
