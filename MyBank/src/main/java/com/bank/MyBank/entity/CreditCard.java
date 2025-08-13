package com.bank.MyBank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cardNumber;
//
//    @ManyToOne(optional = false)
//    private Customer customer;

    // CreditCard.java
    @ManyToOne
    @JsonIgnore
    private Customer customer;

    private BigDecimal creditLimit;
    private BigDecimal currentBalance = BigDecimal.ZERO;

    private LocalDate issuedOn = LocalDate.now();
    private Double aprPercent = 36.0; // simple assumption
}
