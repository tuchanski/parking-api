package com.tuchanski.parking_api.services;

import com.tuchanski.parking_api.entities.User;
import com.tuchanski.parking_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found.")
        );
    }

    @Transactional
    public User updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match.");
        }

        User user = getById(id);

        if (!user.getPassword().equals(currentPassword)) {
            throw new RuntimeException("Passwords do not match.");
        }

        user.setPassword(newPassword);

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
