package com.khoatrbl.ecommerce.service;

import com.khoatrbl.ecommerce.domain.ChangePasswordRequest;
import com.khoatrbl.ecommerce.domain.PatchUserRequest;
import com.khoatrbl.ecommerce.domain.RegisterUserRequest;
import com.khoatrbl.ecommerce.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User registerUser(RegisterUserRequest request);

    List<User> listAllUsers();

    Optional<User> getUserById(UUID userId);

    User patchUser(UUID userId, PatchUserRequest request);

    User changePassword(UUID userId, ChangePasswordRequest request);
}
