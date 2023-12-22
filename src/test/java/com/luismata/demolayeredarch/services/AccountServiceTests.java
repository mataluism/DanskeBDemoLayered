package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.exceptions.account.AccountByIdNotFoundException;
import com.luismata.demolayeredarch.exceptions.withdrawal.WithdrawalInInvalidBalanceException;
import com.luismata.demolayeredarch.exceptions.withdrawal.WithdrawalInsuficientBalanceException;
import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.model.Withdrawal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @BeforeEach
    void init() {
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void givenValidCustomer_whenCallingCreateAccount_thenNewAccountForCustomerCreated(int accountOwnerCustomerId) {
        // given

        //when
        Account newAccount = accountService.createNewAccount(accountOwnerCustomerId);

        //then
        assertNotNull(newAccount);
        assertEquals(newAccount.getAccountOwnerCustomerId(), accountOwnerCustomerId);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 222})
    void givenInvalidCustomer_whenCallingCreateAccount_thenThrowsInvalidCustomerException(int accountOwnerCustomerId) {
        // given

        //when
        Exception exception = assertThrows(HttpClientErrorException.class, () -> accountService.createNewAccount(accountOwnerCustomerId));

        //then
        assertTrue(Objects.requireNonNull(exception.getMessage()).contains("Customer not found."));
    }

    @Test
    void givenValidWithdrawalData_whenCallingWithdrawBalance_thenBalanceGetsUpdated() throws AccountByIdNotFoundException, WithdrawalInsuficientBalanceException, WithdrawalInInvalidBalanceException {
        // given
        int accountId =1;
        long withdrawalAmount = 25;
        Withdrawal withdrawal = new Withdrawal(accountId, withdrawalAmount);
        long oldBalance = accountService.getAccountById(accountId).getBalance();

        // when
        accountService.withdrawBalance(withdrawal);

        // then
        assertEquals(oldBalance-withdrawalAmount, accountService.getAccountById(accountId).getBalance());
    }
}
