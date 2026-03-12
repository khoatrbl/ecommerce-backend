package com.khoatrbl.ecommerce.domain;

import lombok.Builder;

@Builder
public record ChangePasswordRequest(
        String oldPassword,
        String newPassword,
        String confirmNewPassword
) {
}
