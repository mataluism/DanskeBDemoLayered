package com.luismata.demolayeredarch.services;

import com.luismata.demolayeredarch.exceptions.customer.CustomerByIdNotFoundException;
import com.luismata.demolayeredarch.model.Customer;
import com.luismata.demolayeredarch.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerById(int customerId) throws CustomerByIdNotFoundException {
        Optional<Customer> byId = customerRepository.findById(customerId);
        if(byId.isPresent()) {
            return byId.get();
        }
        throw new CustomerByIdNotFoundException("Customer with id: " + customerId + " not found!");
    }

}
