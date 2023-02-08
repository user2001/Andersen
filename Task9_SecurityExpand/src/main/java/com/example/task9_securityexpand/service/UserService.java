package com.example.task9_securityexpand.service;

import com.example.task9_securityexpand.dto.UserRequest;
import com.example.task9_securityexpand.dto.UserResponse;
import com.example.task9_securityexpand.mapper.UserMapper;
import com.example.task9_securityexpand.model.User;
import com.example.task9_securityexpand.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        if (checkExistEmail(user.getEmail())) {
            throw new EntityNotFoundException("User with email: " + user.getEmail() + " already exist");
        }
        userRepository.save(user);
        return userMapper.toDtoResponse(user);
    }

    public UserResponse singUpUser(UserRequest userRequest) {

        if (getByEmail(userRequest.getEmail()) != null) {
            throw new EntityExistsException("User with this email already exist");
        }
        User newUser = new User();
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setRole(userRequest.getRole());
        newUser.setPassword(userRequest.getPassword());
        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);

        return userMapper.toDtoResponse(newUser);
    }

    private User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private boolean checkExistEmail(String email) {
        List<User> users = userRepository.findAll();
        var existedMail = users
                .stream().map(e -> e.getEmail()).filter(e -> e.equals(email)).findFirst();
        return existedMail.isPresent();
    }

    public UserResponse readById(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return userMapper.toDtoResponse(optional.get());
        }
        throw new EntityNotFoundException("User with id: " + id + " not found");
    }

    public UserResponse update(UserRequest userRequest, int user_id) {
        if (userRequest != null) {
            if (getByEmail(userRequest.getEmail()) != null) {
                throw new EntityExistsException("User with this email already exist");
            }
            User oldUser = userRepository.findById(user_id).orElseThrow(
                    () -> new EntityNotFoundException("User with id " + user_id + " not found"));
            oldUser.setName(userRequest.getName());
            oldUser.setPassword(userRequest.getPassword());
            oldUser.setEmail(userRequest.getEmail());
            oldUser.setRole(userRequest.getRole());
            userRepository.save(oldUser);
            return userMapper.toDtoResponse(oldUser);
        }
        throw new IllegalArgumentException("User cannot be 'null'");
    }

    public void delete(int id) {
        UserResponse userResponse = readById(id);
        if (userResponse != null) {
            User user = userMapper.toEntity(userResponse);
            userRepository.delete(user);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDtoResponse).toList();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not Found!");
        }
        return user;
    }
}
