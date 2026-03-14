package com.khoatrbl.ecommerce.controller;

import com.khoatrbl.ecommerce.domain.ChangePasswordRequest;
import com.khoatrbl.ecommerce.domain.PatchUserRequest;
import com.khoatrbl.ecommerce.domain.RegisterUserRequest;
import com.khoatrbl.ecommerce.domain.dto.ChangePasswordRequestDto;
import com.khoatrbl.ecommerce.domain.dto.PatchUserRequestDto;
import com.khoatrbl.ecommerce.domain.dto.RegisterUserRequestDto;
import com.khoatrbl.ecommerce.domain.dto.UserDto;
import com.khoatrbl.ecommerce.domain.entities.User;
import com.khoatrbl.ecommerce.mapper.UserMapper;
import com.khoatrbl.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listAllUsers() {
        List<User> users = userService.listAllUsers();

        List<UserDto> userDtos = users.stream().map(userMapper::toDto).toList();

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") UUID userId) {
        Optional<User> foundUser = userService.getUserById(userId);

        return foundUser.map(user -> {
            UserDto userDto = userMapper.toDto(user);

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/{userId}")
    public ResponseEntity<UserDto> patchUser(@PathVariable("userId") UUID userId,
                                             @Valid @RequestBody PatchUserRequestDto patchUserRequestDto) {

        PatchUserRequest request = userMapper.toRequest(patchUserRequestDto);
        User updatedUser = userService.patchUser(userId, request);
        UserDto userDto = userMapper.toDto(updatedUser);

        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    // id exposure risk
    // TODO: re-model this later
    @PatchMapping(path = "/{userId}/change-password")
    public ResponseEntity<UserDto> updatePassword(@PathVariable("userId") UUID userId,
                                                  @Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {

        ChangePasswordRequest request = userMapper.toRequest(changePasswordRequestDto);

        User user = userService.changePassword(userId, request);
        UserDto userDto = userMapper.toDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
