package com.bank.MyBank.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransferRequest {
    @NotNull
    public Long fromAccountId;
    @NotNull public Long toAccountId;
    @Positive
    public Double amount;
    public String description;
}
