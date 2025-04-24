package com.tuchanski.parking_api.web.controllers;

import com.tuchanski.parking_api.entities.User;
import com.tuchanski.parking_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User userToBeRetrieved = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userToBeRetrieved);
    }



}
