package com.example.andersen.Task7.service;

import com.example.andersen.Task7.model.User;
import com.example.andersen.Task7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user) {
        if (checkExistEmail(user.getEmail())) {
            throw new EntityNotFoundException("User with email: " + user.getEmail() + " already exist");
        }
        return userRepository.save(user);
    }

    private boolean checkExistEmail(String email) {
        List<User> users = userRepository.findAll();
        var existedMail = users
                .stream().map(e -> e.getEmail()).filter(e -> e.equals(email)).findFirst();
        return existedMail.isPresent();
    }

    public User readById(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("User with id: " + id + " not found");
    }

    public User update(User user) {
        if (user != null) {
            User oldUser;
            try {
                oldUser = readById(user.getId());
            } catch (IllegalArgumentException e) {
                throw new EntityNotFoundException("User id cannot be null");
            }
            if (oldUser != null) {
                try {
                    return userRepository.save(user);
                } catch (InvalidDataAccessApiUsageException | IllegalArgumentException e) {
                    throw new EntityNotFoundException("User cannot be null");
                }
            }
        }
        throw new EntityNotFoundException("User can`t be 'null'");
    }

    public void delete(int id) {
        User user = readById(id);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
