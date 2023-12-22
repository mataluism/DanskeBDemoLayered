package com.luismata.demolayeredarch.repositories;

import com.luismata.demolayeredarch.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
