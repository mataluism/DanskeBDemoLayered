package com.luismata.demolayeredarch.repositories;

import com.luismata.demolayeredarch.model.Account;
import com.luismata.demolayeredarch.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {
}
