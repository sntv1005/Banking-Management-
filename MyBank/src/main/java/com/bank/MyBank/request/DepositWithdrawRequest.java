package com.bank.MyBank.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DepositWithdrawRequest {
    @NotNull
    public Long accountId;
    @Positive
    public Double amount;
    public String description;
}
