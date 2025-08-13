package com.bank.MyBank.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class StatementFilter {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate from;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate to;
    public String format = "csv"; // csv default
}
