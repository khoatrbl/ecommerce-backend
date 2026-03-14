package com.khoatrbl.ecommerce.service.impl;

import com.khoatrbl.ecommerce.domain.ChangePasswordRequest;
import com.khoatrbl.ecommerce.domain.PatchUserRequest;
import com.khoatrbl.ecommerce.domain.RegisterUserRequest;
import com.khoatrbl.ecommerce.domain.entities.User;
import com.khoatrbl.ecommerce.domain.entities.UserRole;
import com.khoatrbl.ecommerce.exceptions.PasswordNotMatchException;
import com.khoatrbl.ecommerce.exceptions.UserAlreadyExistException;
import com.khoatrbl.ecommerce.exceptions.UserNotFoundException;
import com.khoatrbl.ecommerce.repositories.UserRepository;
import com.khoatrbl.ecommerce.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(RegisterUserRequest request) {
        if (userExistByUsername(request.username())) {
            throw new UserAlreadyExistException(request.username());
        }

        Instant now = Instant.now();

        String hashedPassword = passwordEncoder.encode(request.password());

        User user = User.builder()
                .userId(null)
                .username(request.username())
                .password(hashedPassword)
                .role(UserRole.USER)
                .displayName(request.displayName())
                .street(request.street())
                .ward(request.ward())
                .city(request.city())
                .createdAt(now)
                .build();

        return userRepository.save(user);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User patchUser(UUID userId, PatchUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        request.username().ifPresent(user::setUsername);
        request.displayName().ifPresent(user::setDisplayName);
        request.street().ifPresent(user::setStreet);
        request.ward().ifPresent(user::setWard);
        request.city().ifPresent(user::setCity);

        return userRepository.save(user);
    }

    @Override
    public User changePassword(UUID userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (!passwordMatched(user.getPassword(), request.oldPassword())) {
            throw new PasswordNotMatchException();
        }

        if (!passwordMatched(request.newPassword(), request.confirmNewPassword())) {
            throw new PasswordNotMatchException();
        }

        user.setPassword(request.newPassword());

        return userRepository.save(user);
    }

    private boolean passwordMatched(String passwordA, String passwordB) {
        return Objects.equals(passwordA, passwordB);
    }

    private boolean userExistByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
