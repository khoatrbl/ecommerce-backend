package com.khoatrbl.ecommerce.exceptions;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException() {
        super("Passwords do not match.");
    }
}
