package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account createNewAccount(int accountOwnerCustomerId) {

        // Add get customer id and validation

        Account newAccount = new Account(accountOwnerCustomerId);
        accountRepository.save(newAccount);
        return newAccount;
    }
}
