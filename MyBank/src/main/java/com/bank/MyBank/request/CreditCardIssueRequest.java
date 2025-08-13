package com.bank.MyBank.request;

import jakarta.validation.constraints.NotNull;

public class CreditCardIssueRequest {
    @NotNull
    public Long customerId;

}
