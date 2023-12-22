package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Exception exception = assertThrows(InvalidCustomerProvidedException.class, () -> accountService.createNewAccount(accountOwnerCustomerId));

        //then
        assertEquals(exception.getMessage(), "Invalid customer id provided." );
    }
}
