package com.khoatrbl.ecommerce.domain;

import lombok.Builder;

@Builder
public record RegisterUserRequest(
        String username,
        String password,
        String displayName,
        String street,
        String ward,
        String city
) {
}
