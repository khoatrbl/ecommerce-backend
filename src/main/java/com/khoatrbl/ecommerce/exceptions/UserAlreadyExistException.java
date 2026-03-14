package com.khoatrbl.ecommerce.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistException extends RuntimeException {
    private String username;

    public UserAlreadyExistException(String username) {
        super(String.format("User with username '%s' already exists.", username));
        this.username = username;
    }
}
