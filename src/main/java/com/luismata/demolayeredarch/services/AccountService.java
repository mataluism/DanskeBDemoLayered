package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.exceptions.account.AccountByIdNotFoundException;
import com.luismata.demolayeredarch.exceptions.customer.InvalidCustomerProvidedException;
import com.luismata.demolayeredarch.exceptions.withdrawal.WithdrawalInvalidBalanceException;
import com.luismata.demolayeredarch.exceptions.withdrawal.WithdrawalInsuficientBalanceException;
import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.model.Customer;
import com.luismata.demolayeredarch.model.Withdrawal;
import com.luismata.demolayeredarch.repositories.AccountRepository;
import com.luismata.demolayeredarch.repositories.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    WithdrawalRepository withdrawalRepository;

    @Autowired
    RestTemplate restTemplate;

    public Account createNewAccount(int accountOwnerCustomerId) {
        // Add get customer id and validation
        String getCustomerByIdEndpoint = "http://localhost:8080/customer/get-customer-byid?customerId={customerId}";

        Customer customerById = restTemplate.getForObject(getCustomerByIdEndpoint, Customer.class, accountOwnerCustomerId);

        if(customerById != null) {
            Account newAccount = new Account(accountOwnerCustomerId);
            accountRepository.save(newAccount);
            return newAccount;
        } else {
            throw new InvalidCustomerProvidedException("Invalid customer id provided.");
        }
    }

    public Account getAccountById(int accountId) throws AccountByIdNotFoundException {
        Optional<Account> byId = accountRepository.findById(accountId);
        if(byId.isPresent()) {
            return byId.get();
        }
        throw new AccountByIdNotFoundException("Account with id: " + accountId + " not found!");
    }

    public void withdrawBalance(Withdrawal withdrawalRequested) throws AccountByIdNotFoundException, WithdrawalInsuficientBalanceException, WithdrawalInvalidBalanceException {
        long withdrawalAmount = withdrawalRequested.getWithdrawalAmount();
        if( withdrawalAmount <= 0) {
            throw new WithdrawalInvalidBalanceException("Invalid balance provided for withdrawal.");
        }
        int accountId = withdrawalRequested.getAccountId();

        Account accountById = getAccountById(accountId);

        long oldBalance = accountById.getBalance();
        long newBalance = oldBalance - withdrawalAmount;

        // Limiting balance to never be below 0. However, rules about having credit or allowing it to be negative could be placed
        if(newBalance >= 0) {
            accountById.setBalance(newBalance);
            accountRepository.save(accountById);

            Withdrawal withdrawal = new Withdrawal(accountId, oldBalance, withdrawalAmount, newBalance);
            withdrawalRepository.save(withdrawal);


        } else {
            throw new WithdrawalInsuficientBalanceException("AccountId: "+ accountId + " doesn't have enough balance to complete transaction. Available balance: "+ oldBalance);
        }
    }
}
