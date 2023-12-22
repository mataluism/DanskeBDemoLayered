package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.exceptions.InvalidCustomerProvidedException;
import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.model.Customer;
import com.luismata.demolayeredarch.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

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
}
