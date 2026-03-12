package com.khoatrbl.ecommerce.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final UUID userId;

    public UserNotFoundException(UUID userId) {
        super(String.format("User with ID '%s' does not exist.", userId));
        this.userId = userId;
    }
}
