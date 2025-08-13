package com.bank.MyBank.service;

import com.bank.MyBank.entity.Account;
import com.bank.MyBank.entity.TransactionType;
import com.bank.MyBank.entity.Txn;
import com.bank.MyBank.exception.BadRequestException;
import com.bank.MyBank.repository.AccountRepo;
import com.bank.MyBank.repository.TxnRepo;
import com.bank.MyBank.request.DepositWithdrawRequest;
import com.bank.MyBank.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TxnService {

    @Autowired
    private AccountRepo accounts;

    @Autowired
    private TxnRepo txns;


    public Account deposit(DepositWithdrawRequest req){
        Account a = accounts.findById(req.accountId)
                .orElseThrow(() -> new BadRequestException("Invalid accountId"));
        BigDecimal amt = BigDecimal.valueOf(req.amount);
        a.setBalance(a.getBalance().add(amt));
        Txn t = new Txn();
        t.setAccount(a); t.setAmount(amt); t.setType(TransactionType.DEPOSIT);
        t.setDescription(req.description == null ? "Deposit" : req.description);
        txns.save(t);
        return accounts.save(a);
    }

    public Account withdraw(DepositWithdrawRequest req){
        Account a = accounts.findById(req.accountId)
                .orElseThrow(() -> new BadRequestException("Invalid accountId"));
        BigDecimal amt = BigDecimal.valueOf(req.amount);
        if (a.getBalance().compareTo(amt) < 0)
            throw new BadRequestException("Insufficient balance");
        a.setBalance(a.getBalance().subtract(amt));
        Txn t = new Txn();
        t.setAccount(a); t.setAmount(amt); t.setType(TransactionType.WITHDRAW);
        t.setDescription(req.description == null ? "Withdraw" : req.description);
        txns.save(t);
        return accounts.save(a);
    }

    public void transfer(TransferRequest req){
        if (req.fromAccountId.equals(req.toAccountId))
            throw new BadRequestException("from and to accounts cannot be same");

        Account from = accounts.findById(req.fromAccountId)
                .orElseThrow(() -> new BadRequestException("Invalid fromAccountId"));
        Account to = accounts.findById(req.toAccountId)
                .orElseThrow(() -> new BadRequestException("Invalid toAccountId"));

        BigDecimal amt = BigDecimal.valueOf(req.amount);
        if (from.getBalance().compareTo(amt) < 0)
            throw new BadRequestException("Insufficient balance");

        from.setBalance(from.getBalance().subtract(amt));
        to.setBalance(to.getBalance().add(amt));

        Txn out = new Txn();
        out.setAccount(from); out.setAmount(amt); out.setType(TransactionType.TRANSFER_OUT);
        out.setDescription(req.description == null ? "Transfer to " + to.getAccountNumber() : req.description);
        txns.save(out);

        Txn in = new Txn();
        in.setAccount(to); in.setAmount(amt); in.setType(TransactionType.TRANSFER_IN);
        in.setDescription(req.description == null ? "Transfer from " + from.getAccountNumber() : req.description);
        txns.save(in);

        accounts.save(from); accounts.save(to);
    }
}
