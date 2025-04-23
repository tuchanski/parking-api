package com.tuchanski.parking_api.web.controllers;

import com.tuchanski.parking_api.entities.User;
import com.tuchanski.parking_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User userToBeCreated = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userToBeCreated);
    }

}
