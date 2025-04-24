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


    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) {
        User userUpdated = userService.updatePassword(id, user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }



}
