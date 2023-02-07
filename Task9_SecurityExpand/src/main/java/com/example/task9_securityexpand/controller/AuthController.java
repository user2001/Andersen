package com.example.task9_securityexpand.controller;

import com.example.task9_securityexpand.dto.auth.JwtResponseDTO;
import com.example.task9_securityexpand.dto.auth.LoginRequestDTO;
import com.example.task9_securityexpand.security.JwtTokenProvider;
import com.example.task9_securityexpand.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public JwtResponseDTO signIn(@RequestBody LoginRequestDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getEmail(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            // Incorrect username or password. find out what's incorrect exactly and return error:
            return authService.getJwtErrorResponse(authenticationDTO.getEmail());
        }

        String token = jwtTokenProvider.generateToken(authenticationDTO.getEmail());
        return authService.getJwtSuccessResponse(authenticationDTO.getEmail(), token);
    }


    @GetMapping("/error/forbidden")
    public String forbiddenError() {
        throw new AccessDeniedException("You cannot access this page");
    }
}
