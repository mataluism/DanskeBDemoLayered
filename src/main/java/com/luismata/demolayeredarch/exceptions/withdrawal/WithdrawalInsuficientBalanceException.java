package com.luismata.demolayeredarch.exceptions.withdrawal;

public class WithdrawalInsuficientBalanceException extends Exception {
    public WithdrawalInsuficientBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
