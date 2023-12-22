package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.exceptions.account.AccountByIdNotFoundException;
import com.luismata.demolayeredarch.exceptions.customer.InvalidCustomerProvidedException;
import com.luismata.demolayeredarch.exceptions.withdrawal.WithdrawalInvalidBalanceException;
import com.luismata.demolayeredarch.exceptions.withdrawal.WithdrawalInsuficientBalanceException;
import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.model.Customer;
import com.luismata.demolayeredarch.model.Withdrawal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    private AccountService mockedAccountService;


    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void givenValidCustomer_whenCallingCreateAccount_thenNewAccountForCustomerCreated(int accountOwnerCustomerId) {
        // given
        Customer testCustomer = new Customer("testName");

        String getCustomerByIdEndpoint = "http://localhost:8080/customer/get-customer-byid?customerId={customerId}";

        //when
        Mockito.when(restTemplate.getForObject(getCustomerByIdEndpoint, Customer.class, accountOwnerCustomerId)).thenReturn(testCustomer);

        Account newAccount = mockedAccountService.createNewAccount(accountOwnerCustomerId);

        //then
        assertNotNull(newAccount);
        assertEquals(newAccount.getAccountOwnerCustomerId(), accountOwnerCustomerId);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 222})
    void givenInvalidCustomer_whenCallingCreateAccount_thenThrowsInvalidCustomerException(int accountOwnerCustomerId) {
        // given
        Customer testCustomer = new Customer("testName");

        String getCustomerByIdEndpoint = "http://localhost:8080/customer/get-customer-byid?customerId={customerId}";

        //when
        Mockito.when(restTemplate.getForObject(getCustomerByIdEndpoint, Customer.class, accountOwnerCustomerId)).thenReturn(null);

        Exception exception = assertThrows(InvalidCustomerProvidedException.class, () -> accountService.createNewAccount(accountOwnerCustomerId));

        //then
        assertTrue(Objects.requireNonNull(exception.getMessage()).contains("Invalid customer id provided."));
    }

    @Test
    void givenValidWithdrawalData_whenCallingWithdrawBalance_thenBalanceGetsUpdated() throws AccountByIdNotFoundException, WithdrawalInsuficientBalanceException, WithdrawalInvalidBalanceException {
        // given
        int accountId =1;
        long withdrawalAmount = 25;
        Withdrawal withdrawal = new Withdrawal(accountId, withdrawalAmount);

        // when
        long oldBalance = accountService.getAccountById(accountId).getBalance();

        accountService.withdrawBalance(withdrawal);

        // then
        assertEquals(oldBalance-withdrawalAmount, accountService.getAccountById(accountId).getBalance());
    }
}
