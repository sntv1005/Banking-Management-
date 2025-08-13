package com.bank.MyBank.exception;

import java.time.LocalDateTime;

public class ApiError {
    public LocalDateTime timestamp = LocalDateTime.now();
    public String message;
    public String path;

    public ApiError(String message, String path){
        this.message = message; this.path = path;
    }
}