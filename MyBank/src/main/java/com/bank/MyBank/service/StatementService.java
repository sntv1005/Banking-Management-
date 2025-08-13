package com.bank.MyBank.service;

import com.bank.MyBank.entity.Txn;
import com.bank.MyBank.repository.TxnRepo;
import com.bank.MyBank.request.StatementFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private TxnRepo txnRepo;

    public List<Txn> accountStatement(Long accountId, StatementFilter filter){
        LocalDateTime from = filter.from == null ? LocalDateTime.now().minusYears(10) : filter.from.atStartOfDay();
        LocalDateTime to = filter.to == null ? LocalDateTime.now() : filter.to.atTime(23,59,59);
        return txnRepo.findByAccountIdAndCreatedAtBetween(accountId, from, to);
    }
}
