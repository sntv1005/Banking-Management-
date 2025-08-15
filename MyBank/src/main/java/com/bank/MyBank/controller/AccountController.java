package com.bank.MyBank.controller;

import com.bank.MyBank.entity.Account;
import com.bank.MyBank.repository.AccountRepo;
import com.bank.MyBank.request.CreateAccountRequest;
import com.bank.MyBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<Account> getAccountByCustomerId(@PathVariable Long customerId) {
        Account account = accountService.getAccountByCustomerId(customerId);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        if (accountService.getAccountByCustomerId(createAccountRequest.customerId) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Account already exists for this customer");
        }
        Account created = accountService.createAccount(createAccountRequest);
        return ResponseEntity.ok(created);
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
