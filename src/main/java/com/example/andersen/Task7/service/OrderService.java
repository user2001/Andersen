package com.example.andersen.Task7.service;

import com.example.andersen.Task7.mapper.ProductMapper;
import com.example.andersen.Task7.model.Order;
import com.example.andersen.Task7.repository.OrderRepository;
import com.example.andersen.Task7.repository.ProductRepository;
import com.example.andersen.Task7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final ProductMapper productMapper;

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order getBucketById(int orderId) {
        return orderRepository.findById(orderId).get();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getByUserId(int userId) {
        List<Order> orders = orderRepository.getByUserId(userId);
        return orders.isEmpty() ? new ArrayList<>() : orders;
    }

    public Order readById(int id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Order with id " + id + " not found");
    }

    public void delete(int id) {
        Order order = readById(id);

        if (order == null) {
            throw new EntityNotFoundException("Order with id " + id + " not found");
        }
        orderRepository.delete(order);
    }

    public Order update(Order order) {

        if (order == null) {
            throw new EntityNotFoundException("ToDo can`t be null");
        }
        Order oldOrder;
        try {
            oldOrder = readById(order.getId());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Order with id " + order.getId() + " not found");
        }

        if (oldOrder == null) {
            throw new EntityNotFoundException("Order can`t be null");
        }
        return orderRepository.save(order);
    }
    public void confirmOrder(int order_id){
        Order order = readById(order_id);
        order.setSubmit(true);
        orderRepository.save(order);
    }


}
