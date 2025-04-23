package com.tuchanski.parking_api.web.controllers;

import com.tuchanski.parking_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

}
