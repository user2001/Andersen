package com.example.task9_securityexpand.controller;

import com.example.task9_securityexpand.dto.OrderResponse;
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
@RequestMapping("/api/v2/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;


    @GetMapping("/all/users/{owner_id}")
    @PreAuthorize("hasAuthority('USER') and authentication.principal.id == #ownerId")
    public Map<String, Object> getAllUsersOrders(@PathVariable("owner_id") int ownerId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.toString());
        response.put("orders", orderService.getByUserId(ownerId));
        response.put("user", userService.readById(ownerId));
        return response;

    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{order_id}/read/users/{owner_id}")
    @PreAuthorize("hasAuthority('USER') and authentication.principal.id == #ownerId")
    public Map<String, Object> getOrderById(@PathVariable("order_id") int orderId,
                                            @PathVariable("owner_id") int ownerId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.toString());
        response.put("order", orderService.readById(orderId));
        response.put("products", productService.getByOrderId(orderId));
        return response;
    }

    @DeleteMapping("/{order_id}/delete/users/{owner_id}")
    @PreAuthorize("hasAuthority('USER') and authentication.principal.id == #ownerId")
    @ResponseStatus(HttpStatus.OK)
    public String removeOrder(@PathVariable("order_id") int orderId,
                              @PathVariable("owner_id") int ownerId) {
        orderService.delete(orderId);
        return "Order with id:" + orderId + " deleted";
    }

    @PostMapping("/create/users/{owner_id}")
    @PreAuthorize("hasAuthority('USER') and authentication.principal.id == #ownerId")
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@PathVariable("owner_id") int ownerId) {
        orderService.create(ownerId);
        return "Order was created";
    }

    @GetMapping("/{order_id}/users/{owner_id}/products")
    @PreAuthorize("hasAuthority('USER') and authentication.principal.id == #ownerId")
    public Map<String, Object> getOrderProducts(@PathVariable("order_id") int orderId,
                                                @PathVariable("owner_id") int ownerId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.toString());
        response.put("body", orderService.getOrderProducts(orderId));
        return response;
    }

    @PatchMapping("/{orderId}/users/{owner_id}/add/product")
    public Map<String, Object> addProduct(@PathVariable int orderId,
                                          @PathVariable("owner_id") int ownerId,
                                          @RequestParam("product_id") int productId) {
        orderService.addProduct(orderId, productId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.toString());
        response.put("body", orderService.getById(orderId));
        return response;
    }

    @PatchMapping("/{orderId}/users/{owner_id}/remove/product")
    @PreAuthorize("hasAuthority('USER') and authentication.principal.id == #ownerId")
    public Map<String, Object> removeProduct(@PathVariable int orderId,
                                             @PathVariable("owner_id") int ownerId,
                                             @RequestParam("product_id") int productId) {
        orderService.removeProduct(orderId, productId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.toString());
        return response;
    }
}
