package com.luismata.demolayeredarch.controllers;

import com.luismata.demolayeredarch.exceptions.customer.CustomerByIdNotFoundException;
import com.luismata.demolayeredarch.model.Customer;
import com.luismata.demolayeredarch.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Customer> getCustomerById(@RequestParam int customerId) {
        Customer customerById;

        try {
            customerById = customerService.getCustomerById(customerId);
        }
        catch (CustomerByIdNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }

        return ResponseEntity.ok().body(customerById);

    }
}
