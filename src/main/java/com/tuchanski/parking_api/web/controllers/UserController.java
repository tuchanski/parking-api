package com.tuchanski.parking_api.web.controllers;

import com.tuchanski.parking_api.entities.User;
import com.tuchanski.parking_api.services.UserService;
import com.tuchanski.parking_api.web.dto.UserCreateDto;
import com.tuchanski.parking_api.web.dto.UserResponseDto;
import com.tuchanski.parking_api.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto userDto) {

        User userToBeCreated = userService.save(UserMapper.toUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(userToBeCreated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User userToBeRetrieved = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(userToBeRetrieved));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) {
        User userUpdated = userService.updatePassword(id, user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> usersToBeRetrieved = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(usersToBeRetrieved);
    }



}
