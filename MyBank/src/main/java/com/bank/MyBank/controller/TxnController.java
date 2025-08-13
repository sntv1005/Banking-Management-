package com.bank.MyBank.controller;

import com.bank.MyBank.entity.Account;
import com.bank.MyBank.request.DepositWithdrawRequest;
import com.bank.MyBank.request.TransferRequest;
import com.bank.MyBank.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/deposit")
    public Account deposit(@RequestBody DepositWithdrawRequest req){
        return txnService.deposit(req);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestBody DepositWithdrawRequest req){
        return txnService.withdraw(req);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest req) {
        txnService.transfer(req);
        return ResponseEntity.ok("{\"message\":\"Transfer successful\"}");
    }
}
