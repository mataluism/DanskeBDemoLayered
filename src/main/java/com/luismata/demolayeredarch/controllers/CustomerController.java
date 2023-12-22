package com.luismata.demolayeredarch.controllers;

import com.luismata.demolayeredarch.exceptions.CustomerByIdNotFound;
import com.luismata.demolayeredarch.model.Customer;
import com.luismata.demolayeredarch.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/get-customer-byid")
    public Customer getCustomerById(@RequestParam int customerId) {
        try {
            return customerService.getCustomerById(customerId);
        }
        catch (CustomerByIdNotFound exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer not found.", exc);
        }

    }
}
