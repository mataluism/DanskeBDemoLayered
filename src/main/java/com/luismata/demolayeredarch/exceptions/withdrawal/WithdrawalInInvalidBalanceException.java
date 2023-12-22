package com.luismata.demolayeredarch.exceptions.withdrawal;

public class WithdrawalInInvalidBalanceException extends Exception {
    public WithdrawalInInvalidBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
