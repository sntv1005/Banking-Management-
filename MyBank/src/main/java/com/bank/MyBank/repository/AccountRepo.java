package com.bank.MyBank.repository;

import com.bank.MyBank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByCustomerId(Long customerId);
}
