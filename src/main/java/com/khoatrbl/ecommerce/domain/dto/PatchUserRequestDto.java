package com.khoatrbl.ecommerce.domain.dto;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record PatchUserRequestDto(
        @Length(min = 3, max = 40, message = ERROR_MESSAGE_USERNAME_LENGTH)
        @Pattern(regexp = "^[a-z0-9_]+$", message = ERROR_MESSAGE_USERNAME_FORMAT)
        String username,

        @Length(min = 3, max = 40, message = ERROR_MESSAGE_DISPLAY_NAME_LENGTH)
        String displayName,
        String street,
        String ward,
        String city
) {
    private static final String ERROR_MESSAGE_USERNAME_LENGTH = "Username must be between 3 and 40 characters.";
    private static final String ERROR_MESSAGE_USERNAME_FORMAT = "Username can only contain letters, numbers and underscores.";
    private static final String ERROR_MESSAGE_DISPLAY_NAME_LENGTH = "Display name must be between 3 and 40 characters";
}
