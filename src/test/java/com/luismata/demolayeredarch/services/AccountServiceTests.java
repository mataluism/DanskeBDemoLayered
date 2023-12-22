package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @BeforeEach
    void init() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void givenValidCustomer_whenCallingCreateAccount_thenNewAccountForCustomerCreated(int accountOwnerCustomerId) {
        // given

        //when
        Account newAccount = accountService.createNewAccount(accountOwnerCustomerId);

        //then
        assertNotNull(newAccount);
        assertEquals(newAccount.getAccountOwnerCustomerId(), accountOwnerCustomerId);
    }
}
