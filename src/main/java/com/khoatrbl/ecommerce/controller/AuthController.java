package com.khoatrbl.ecommerce.controller;

import com.khoatrbl.ecommerce.domain.LoginRequest;
import com.khoatrbl.ecommerce.domain.RegisterUserRequest;
import com.khoatrbl.ecommerce.domain.dto.AuthResponse;
import com.khoatrbl.ecommerce.domain.dto.RegisterUserRequestDto;
import com.khoatrbl.ecommerce.domain.dto.UserDto;
import com.khoatrbl.ecommerce.domain.entities.User;
import com.khoatrbl.ecommerce.mapper.UserMapper;
import com.khoatrbl.ecommerce.service.AuthenticationService;
import com.khoatrbl.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(AuthenticationService authenticationService, UserService userService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        RegisterUserRequest request = userMapper.toRequest(registerUserRequestDto);

        User registeredUser = userService.registerUser(request);
        UserDto userDto = userMapper.toDto(registeredUser);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        String token = authenticationService.generateToken(userDetails);

        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .expiresIn(86400)
                .build();

        return ResponseEntity.ok(authResponse);
    }

}
