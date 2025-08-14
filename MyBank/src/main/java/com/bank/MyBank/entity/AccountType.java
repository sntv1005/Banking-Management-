package com.bank.MyBank.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.*;

public enum AccountType {
    SAVINGS, CURRENT;

    @JsonCreator
    public static AccountType fromString(String value) {
        return Arrays.stream(AccountType.values())
                .filter(type -> type.name().equalsIgnoreCase(value) ||
                        (value.equalsIgnoreCase("SAVING") && type == SAVINGS))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid account type: " + value));
    }
}
