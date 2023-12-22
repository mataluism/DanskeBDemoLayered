package com.luismata.demolayeredarch.exceptions.withdrawal;

public class WithdrawalInvalidBalanceException extends Exception {
    public WithdrawalInvalidBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
