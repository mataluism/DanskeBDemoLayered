package com.luismata.demolayeredarch.controllers;

import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Account createNewAccount(@RequestParam int accountOwnerCustomerId) {
        return accountService.createNewAccount(accountOwnerCustomerId);
    }
}
