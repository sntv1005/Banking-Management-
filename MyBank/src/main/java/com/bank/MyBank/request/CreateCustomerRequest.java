package com.bank.MyBank.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCustomerRequest {
    @NotBlank public String name;
    @NotNull public String dateOfBirth;
    @NotBlank public String gender;

    @Email @NotBlank public String email;
    @NotBlank public String phone;

    @NotBlank public String addressLine;
    public String city;
    public String state;
    public String postalCode;
    public String country;

    @NotBlank public String governmentIdType;
    @NotBlank public String governmentIdNumber;

    @NotBlank public String password;
    @PositiveOrZero public Double monthlyIncome;
    public String occupation;
}
