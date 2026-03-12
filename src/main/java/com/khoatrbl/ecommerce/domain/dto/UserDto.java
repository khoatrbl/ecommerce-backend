package com.khoatrbl.ecommerce.domain.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto(
        UUID userId,
        String username,
        String displayName,
        String street,
        String ward,
        String city
) {

}
