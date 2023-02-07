package com.example.task9_securityexpand.controller;

import com.example.task9_securityexpand.dto.OrderRequest;
import com.example.task9_securityexpand.dto.OrderResponse;
import com.example.task9_securityexpand.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String removeOrder(@PathVariable int id) {
        orderService.delete(id);
        return "Order with id:" + id + " deleted";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest){
        orderService.create(orderRequest);
        return "Order was created";
    }

    @GetMapping("/{order_id}/products")
    public Map<String, Object> getOrderProducts(@PathVariable("order_id") int orderId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.toString());
        response.put("body", orderService.getOrderProducts(orderId));
        return response;
    }

    @PatchMapping("/{id}/add/product")
    public Map<String, Object> addProduct(@PathVariable("id") int orderId, @RequestParam("product_id") int productId) {
        orderService.addProduct(orderId, productId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.toString());
        return response;
    }

    @PatchMapping("/{id}/remove/product")
    public Map<String, Object> removeProduct(@PathVariable("id") int orderId, @RequestParam("product_id") int productId) {
        orderService.removeProduct(orderId, productId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.toString());
        return response;
    }
}
