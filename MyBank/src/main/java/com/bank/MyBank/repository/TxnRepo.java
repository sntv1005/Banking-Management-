package com.bank.MyBank.repository;

import com.bank.MyBank.entity.Txn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TxnRepo extends JpaRepository<Txn,Long> {
    List<Txn> findByAccountIdAndCreatedAtBetween(Long accountId, LocalDateTime from, LocalDateTime to);
    List<Txn> findByAccountIdOrderByCreatedAtDesc(Long accountId);
}
