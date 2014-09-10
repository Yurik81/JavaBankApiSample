package com.exceptions;

public class BankApiException extends Exception {
    public BankApiException(String message)
    {
        super(message);
    }
}
