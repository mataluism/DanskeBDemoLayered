package com.luismata.demolayeredarch.controllers;

import com.luismata.demolayeredarch.exceptions.customer.InvalidCustomerProvidedException;
import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.model.Withdrawal;
import com.luismata.demolayeredarch.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/check-app")
    public String checkApp() {
        return "Hello from check-app";
    }

    @PostMapping("/create-new-account")
    public ResponseEntity<Account> createNewAccount(@RequestParam int accountOwnerCustomerId) {
        Account newAccount;
        try {
            newAccount = accountService.createNewAccount(accountOwnerCustomerId);
        } catch (InvalidCustomerProvidedException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }

        return ResponseEntity.ok().body(newAccount);
    }

    @PostMapping(path="/withdraw-balance", consumes = "application/json")
    public void withdraw(@Valid @RequestBody Withdrawal withdrawal) {
        try {
            accountService.withdrawBalance(withdrawal);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }
}
