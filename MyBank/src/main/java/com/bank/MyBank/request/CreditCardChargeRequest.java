package com.bank.MyBank.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreditCardChargeRequest {
    @NotNull
    public Long cardId;
    @Positive
    public Double amount;
    public String description;
    public boolean payment = false; // if true, treat amount as repayment
}
