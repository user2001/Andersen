package com.example.task9_securityexpand.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    private String email;
    private String password;
}
