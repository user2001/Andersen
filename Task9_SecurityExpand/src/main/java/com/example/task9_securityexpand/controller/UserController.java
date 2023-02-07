package com.example.task9_securityexpand.controller;

import com.example.task9_securityexpand.dto.OrderResponse;
import com.example.task9_securityexpand.dto.UserRequest;
import com.example.task9_securityexpand.dto.UserResponse;
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
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.create(userRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "user deleted";
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id) {
        return userService.readById(id);
    }

   // @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{userId}/orders")
    public List<OrderResponse> getUsersOrders(@PathVariable int userId) {
        return orderService.getByUserId(userId);
    }

    @GetMapping("/{userId}/orders/{orderId}/products")
    public Map<String, Object> getUsersProductsFromOrder(@PathVariable int userId, @PathVariable int orderId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.toString());
        response.put("order", orderService.getByUserId(userId));
        response.put("products", productService.getByOrderId(orderId));
        return response;
    }


}
