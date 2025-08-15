package com.bank.MyBank.service;

import com.bank.MyBank.entity.Account;
import com.bank.MyBank.entity.AccountType;
import com.bank.MyBank.entity.Customer;
import com.bank.MyBank.exception.BadRequestException;
import com.bank.MyBank.repository.AccountRepo;
import com.bank.MyBank.repository.CustomerRepo;
import com.bank.MyBank.request.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public Account createAccount(CreateAccountRequest createAccountRequest) {
        Customer c = customerRepo.findById(createAccountRequest.customerId)
                .orElseThrow(() -> new BadRequestException("Invalid customerId"));
        Account a = new Account();
        a.setCustomer(c);
        a.getCustomer().setEmail(c.getEmail());
        a.getCustomer().setDateOfBirth(c.getDateOfBirth());
        a.setType(createAccountRequest.type == null ? AccountType.SAVINGS : createAccountRequest.type);
        a.setAccountNumber("ACC-" + UUID.randomUUID().toString().substring(0,8).toUpperCase());
        a.setBalance(BigDecimal.valueOf(createAccountRequest.initialDeposit == null ? 0.0 : createAccountRequest.initialDeposit));
        return accountRepo.save(a);

    }

    public Optional<Account> getById(Long accountId) {
        Optional<Account> account = accountRepo.findById(accountId);
        return account;
    }

    public Account get(Long id){
        return accountRepo.findById(id).orElseThrow(() -> new BadRequestException("Account not found: " + id));
    }

    public void save(Account a){ accountRepo.save(a); }


}
