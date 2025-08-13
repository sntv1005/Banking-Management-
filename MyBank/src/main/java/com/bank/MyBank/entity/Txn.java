package com.bank.MyBank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Txn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(optional = false)
//    private Account account;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;


    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();
}
