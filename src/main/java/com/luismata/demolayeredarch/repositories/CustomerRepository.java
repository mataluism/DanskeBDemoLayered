package com.luismata.demolayeredarch.repositories;

import com.luismata.demolayeredarch.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
