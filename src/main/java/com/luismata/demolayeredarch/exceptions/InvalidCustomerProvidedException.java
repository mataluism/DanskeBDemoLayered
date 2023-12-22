package com.luismata.demolayeredarch.exceptions;

public class InvalidCustomerProvidedException extends IllegalArgumentException{
    public InvalidCustomerProvidedException(String errorMessage) {
        super(errorMessage);
    }
}
