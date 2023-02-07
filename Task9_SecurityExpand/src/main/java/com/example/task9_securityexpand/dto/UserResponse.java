package com.example.task9_securityexpand.dto;

import com.example.task9_securityexpand.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String name;
    private Role role;
    private String email;
    private String password;
}
