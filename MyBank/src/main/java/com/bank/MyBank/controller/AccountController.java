package com.bank.MyBank.controller;

import com.bank.MyBank.entity.Account;
import com.bank.MyBank.repository.AccountRepo;
import com.bank.MyBank.request.CreateAccountRequest;
import com.bank.MyBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepo accountRepo;

    @PostMapping("/create")
    public Account createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping("/getAccount")
    Optional<Account> getAccountDetails(@RequestParam Long accountId) {
        return accountService.getById(accountId);
    }

    @GetMapping("/getAll")
    public List<Account> all(){
        return accountRepo.findAll();
    }

    @GetMapping("/{id}")
    public Account one(@PathVariable Long id){
        return accountService.get(id);
    }
}
