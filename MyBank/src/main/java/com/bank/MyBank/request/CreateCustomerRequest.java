package com.bank.MyBank.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateCustomerRequest {
    @NotBlank
    public String name;
    @Email
    @NotBlank public String email;
    @NotBlank public String phone;
    @PositiveOrZero
    public Double monthlyIncome;
}
