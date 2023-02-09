package com.example.task9_securityexpand.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseDTO {
    private String accessToken;
    private String exception;
    private String userRole;
    private long userId;
}
