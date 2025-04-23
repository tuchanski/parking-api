package com.tuchanski.parking_api.services;

import com.tuchanski.parking_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository userRepository;

}
