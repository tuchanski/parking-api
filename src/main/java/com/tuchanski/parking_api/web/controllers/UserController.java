package com.tuchanski.parking_api.web.controllers;

import com.tuchanski.parking_api.entities.User;
import com.tuchanski.parking_api.services.UserService;
import com.tuchanski.parking_api.web.dto.UserCreateDto;
import com.tuchanski.parking_api.web.dto.UserPasswordDto;
import com.tuchanski.parking_api.web.dto.UserResponseDto;
import com.tuchanski.parking_api.web.dto.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Users", description = "Contains all operations to Create, Read, Update & Delete users.")

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user", responses = {
            @ApiResponse(responseCode = "201", description = "Resource created successfully.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Username already registered.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "422", description = "Resource not processed by invalid fields.", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto userDto) {

        User userToBeCreated = userService.save(UserMapper.toUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(userToBeCreated));
    }

    @Operation(summary = "Retrieve a user by id", responses = {
            @ApiResponse(responseCode = "200", description = "Resource retrieved successfully.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Resource not found.", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User userToBeRetrieved = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(userToBeRetrieved));
    }

    @Operation(summary = "Update user password", responses = {
            @ApiResponse(responseCode = "204", description = "Password updated successfully.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Resource not found.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Passwords do not match.", content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDto userPasswordDto) {
        userService.updatePassword(id, userPasswordDto.getCurrentPassword(), userPasswordDto.getNewPassword(), userPasswordDto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retrieve all users", responses = {
            @ApiResponse(responseCode = "200", description = "Resource retrieved successfully.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {

        List<User> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListDto(users));
    }
    
}
