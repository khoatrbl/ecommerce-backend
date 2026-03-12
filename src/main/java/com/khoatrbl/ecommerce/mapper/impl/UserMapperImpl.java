package com.khoatrbl.ecommerce.mapper.impl;

import com.khoatrbl.ecommerce.domain.ChangePasswordRequest;
import com.khoatrbl.ecommerce.domain.PatchUserRequest;
import com.khoatrbl.ecommerce.domain.RegisterUserRequest;
import com.khoatrbl.ecommerce.domain.dto.ChangePasswordRequestDto;
import com.khoatrbl.ecommerce.domain.dto.PatchUserRequestDto;
import com.khoatrbl.ecommerce.domain.dto.RegisterUserRequestDto;
import com.khoatrbl.ecommerce.domain.dto.UserDto;
import com.khoatrbl.ecommerce.domain.entities.User;
import com.khoatrbl.ecommerce.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public RegisterUserRequest toRequest(RegisterUserRequestDto dto) {
        return RegisterUserRequest.builder()
                .username(dto.username())
                .password(dto.password())
                .displayName(dto.displayName())
                .street(dto.street())
                .ward(dto.ward())
                .city(dto.city())
                .build();
    }

    @Override
    public PatchUserRequest toRequest(PatchUserRequestDto dto) {
        return PatchUserRequest.builder()
                .username(Optional.ofNullable(dto.username()))
                .displayName(Optional.ofNullable(dto.displayName()))
                .street(Optional.ofNullable(dto.street()))
                .ward(Optional.ofNullable(dto.ward()))
                .city(Optional.ofNullable(dto.city()))
                .build();
    }

    @Override
    public ChangePasswordRequest toRequest(ChangePasswordRequestDto dto) {
        return ChangePasswordRequest.builder()
                .oldPassword(dto.oldPassword())
                .newPassword(dto.newPassword())
                .confirmNewPassword(dto.confirmNewPassword())
                .build();
    }

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .street(user.getStreet())
                .ward(user.getWard())
                .city(user.getCity())
                .build();
    }
}
