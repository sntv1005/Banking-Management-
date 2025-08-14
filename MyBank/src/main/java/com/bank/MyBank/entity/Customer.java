package com.bank.MyBank.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB primary key

    @Column(unique = true, nullable = false)
    private String customerId; // e.g., CUS-XXXXXX

    @NotBlank private String name;
    private LocalDate dateOfBirth;
    private String gender;

    @Email
    @NotBlank private String email;

    @NotBlank private String phone;

    @NotBlank private String addressLine;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @NotBlank private String governmentIdType;
    @NotBlank private String governmentIdNumber;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?!.*(012|123|234|345|456|567|678|789|abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz)).*$",
            message = "Password must not contain sequential numbers or alphabets"
    )
    private String password;

    @PositiveOrZero
    private Double monthlyIncome;

    private String occupation;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCard> creditCards = new ArrayList<>();
}
