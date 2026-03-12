package com.khoatrbl.ecommerce.mapper;

import com.khoatrbl.ecommerce.domain.ChangePasswordRequest;
import com.khoatrbl.ecommerce.domain.PatchUserRequest;
import com.khoatrbl.ecommerce.domain.RegisterUserRequest;
import com.khoatrbl.ecommerce.domain.dto.ChangePasswordRequestDto;
import com.khoatrbl.ecommerce.domain.dto.PatchUserRequestDto;
import com.khoatrbl.ecommerce.domain.dto.RegisterUserRequestDto;
import com.khoatrbl.ecommerce.domain.dto.UserDto;
import com.khoatrbl.ecommerce.domain.entities.User;

public interface UserMapper {
    // From Controller Request to Service DTO
    RegisterUserRequest toRequest(RegisterUserRequestDto dto);
    PatchUserRequest toRequest(PatchUserRequestDto dto);
    ChangePasswordRequest toRequest(ChangePasswordRequestDto dto);

    // From Entity to Domain DTO
    UserDto toDto(User user);


}
