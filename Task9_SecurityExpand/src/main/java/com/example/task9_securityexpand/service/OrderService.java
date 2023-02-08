package com.example.task9_securityexpand.service;

import com.example.task9_securityexpand.dto.OrderRequest;
import com.example.task9_securityexpand.dto.OrderResponse;
import com.example.task9_securityexpand.dto.ProductResponse;
import com.example.task9_securityexpand.dto.UserResponse;
import com.example.task9_securityexpand.mapper.OrderMapper;
import com.example.task9_securityexpand.mapper.ProductMapper;
import com.example.task9_securityexpand.mapper.UserMapper;
import com.example.task9_securityexpand.model.Order;
import com.example.task9_securityexpand.model.Product;
import com.example.task9_securityexpand.model.User;
import com.example.task9_securityexpand.repository.OrderRepository;
import com.example.task9_securityexpand.repository.ProductRepository;
import com.example.task9_securityexpand.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final OrderRepository orderRepository;
    private final UserMapper userMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public void create(int userId) {
        Order order = new Order();
        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
        order.setOwner(user);
        orderRepository.save(order);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
    }

    public OrderResponse getById(int orderId) {
        Order order = orderRepository.findById(orderId).get();
        return orderMapper.toDto(order);
    }

    public void delete(int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order with id:" + orderId + " not found"));
        orderRepository.delete(order);
    }

    public OrderResponse readById(int id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            return orderMapper.toDto(optional.get());
        }
        throw new EntityNotFoundException("Order with id " + id + " not found");
    }

    public List<ProductResponse> getOrderProducts(int orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + orderId + " not found"));

        return order.getOrderedProducts()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
    public List<Order> getByOwner(User user ){
        return orderRepository.findAllByOwner(user);
    }

    @Transactional
    public void addProduct(int orderId, int productId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + orderId + " not found"));

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product with id " + productId + " not found"));

        List<Product> orderProducts = order.getOrderedProducts();
        orderProducts.add(product);
    }


    @Transactional
    public void removeProduct(int orderId, int productId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + orderId + " not found"));

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException("Product with id " + productId + " not found"));

        List<Product> orderProducts = order.getOrderedProducts();
        orderProducts.remove(product);
    }

    public List<OrderResponse> getByUserId(int userId) {
        List<Order> byUserId = orderRepository.getByUserId(userId);
        return byUserId.stream().map(orderMapper::toDto).toList();
    }
}

