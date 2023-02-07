package com.example.task9_securityexpand.service;

import com.example.task9_securityexpand.dto.auth.JwtResponseDTO;
import com.example.task9_securityexpand.model.User;
import com.example.task9_securityexpand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService  {
    private final UserRepository userRepository;


    public JwtResponseDTO getJwtErrorResponse(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        String errorMessage = user.isPresent()
                ? "Incorrect password"
                : "User not found";
        return new JwtResponseDTO("none", errorMessage, "none", -1);
    }

    public JwtResponseDTO getJwtSuccessResponse(String email, String token) {
        User user = userRepository.findByEmail(email);
        return new JwtResponseDTO(
                token, "none",
                user.getRole().name(),
                user.getId()
        );
    }
}
