package com.khoatrbl.ecommerce.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RegisterUserRequestDto(
        @NotBlank(message = ERROR_MESSAGE_USERNAME_LENGTH)
        @Length(min = 3, max = 40, message = ERROR_MESSAGE_USERNAME_LENGTH)
        @Pattern(regexp = "^[a-z0-9_]+$", message = ERROR_MESSAGE_USERNAME_FORMAT)
        String username,

        @NotBlank(message = ERROR_MESSAGE_PASSWORD_LENGTH)
        @Length(min = 5, message = ERROR_MESSAGE_PASSWORD_LENGTH)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = ERROR_MESSAGE_PASSWORD_FORMAT)
        String password,

        @NotBlank(message = ERROR_MESSAGE_DISPLAY_NAME_LENGTH)
        @Length(min = 3, max = 40, message = ERROR_MESSAGE_DISPLAY_NAME_LENGTH)
        String displayName,

        @NotBlank(message = ERROR_MESSAGE_ADDRESS_LENGTH)
        String street,

        @NotBlank(message = ERROR_MESSAGE_ADDRESS_LENGTH)
        String ward,

        @NotBlank(message = ERROR_MESSAGE_ADDRESS_LENGTH)
        String city
) {
    private static final String ERROR_MESSAGE_USERNAME_LENGTH = "Username must be between 3 and 40 characters.";
    private static final String ERROR_MESSAGE_USERNAME_FORMAT = "Username can only contain letters, numbers and underscores.";
    private static final String ERROR_MESSAGE_PASSWORD_LENGTH = "Password must contain 5 or more characters.";
    private static final String ERROR_MESSAGE_PASSWORD_FORMAT = "Password must contain at least one uppercase letter and a special character.";
    private static final String ERROR_MESSAGE_DISPLAY_NAME_LENGTH = "Display name must be between 3 and 40 characters";
    private static final String ERROR_MESSAGE_ADDRESS_LENGTH = "Please enter a valid address.";
}
