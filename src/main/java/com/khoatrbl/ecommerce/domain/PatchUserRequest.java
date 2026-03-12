package com.khoatrbl.ecommerce.domain;

import lombok.Builder;

import java.util.Optional;

@Builder
public record PatchUserRequest(
        Optional<String> username,
        Optional<String> displayName,
        Optional<String> street,
        Optional<String> ward,
        Optional<String> city
) {
}
