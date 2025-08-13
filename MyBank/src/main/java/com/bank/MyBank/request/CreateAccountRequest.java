package com.bank.MyBank.request;

import com.bank.MyBank.entity.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateAccountRequest {

    @NotNull
    public Long customerId;
    @NotNull public AccountType type;
    @PositiveOrZero
    public Double initialDeposit = 0.0;
}
