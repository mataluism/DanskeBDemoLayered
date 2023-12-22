package com.luismata.demolayeredarch.exceptions.customer;

public class InvalidCustomerProvidedException extends IllegalArgumentException{
    public InvalidCustomerProvidedException(String errorMessage) {
        super(errorMessage);
    }
}
