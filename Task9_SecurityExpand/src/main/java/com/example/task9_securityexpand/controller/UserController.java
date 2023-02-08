package com.example.task9_securityexpand.controller;

import com.example.task9_securityexpand.dto.OrderResponse;
import com.example.task9_securityexpand.dto.UserRequest;
import com.example.task9_securityexpand.dto.UserResponse;
import com.example.task9_securityexpand.model.Order;
import com.example.task9_securityexpand.service.OrderService;
import com.example.task9_securityexpand.service.ProductService;
import com.example.task9_securityexpand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')" +"or authentication.principal.userId == #id")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "user deleted";
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id) {
        return userService.readById(id);
    }


}
