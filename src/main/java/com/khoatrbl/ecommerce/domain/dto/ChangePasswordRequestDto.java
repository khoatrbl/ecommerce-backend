package com.khoatrbl.ecommerce.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record ChangePasswordRequestDto(
        @NotBlank(message = "Please enter your previous password.")
        String oldPassword,

        @NotBlank(message = ERROR_MESSAGE_PASSWORD_LENGTH)
        @Length(min = 5, message = ERROR_MESSAGE_PASSWORD_LENGTH)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = ERROR_MESSAGE_PASSWORD_FORMAT)
        String newPassword,

        @NotBlank(message = "Please confirm your password.")
        String confirmNewPassword
) {
        private static final String ERROR_MESSAGE_PASSWORD_LENGTH = "Password must contain 5 or more characters.";
        private static final String ERROR_MESSAGE_PASSWORD_FORMAT = "Password must contain at least one uppercase letter and a special character.";
}
