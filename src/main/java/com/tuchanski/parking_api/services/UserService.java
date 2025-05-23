package com.tuchanski.parking_api.services;

import com.tuchanski.parking_api.entities.User;
import com.tuchanski.parking_api.exception.EntityNotFoundException;
import com.tuchanski.parking_api.exception.UsernameUniqueViolationException;
import com.tuchanski.parking_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username %s already exists", user.getUsername()));
        }

    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with id %s not found", id))
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
