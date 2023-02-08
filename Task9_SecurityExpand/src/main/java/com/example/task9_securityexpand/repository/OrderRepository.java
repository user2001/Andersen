package com.example.task9_securityexpand.repository;


import com.example.task9_securityexpand.model.Order;
import com.example.task9_securityexpand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select id, owner_id from orders where owner_id = ?1 ",
            nativeQuery = true)
    List<Order> getByUserId(int userId);

    List<Order> findAllByOwner(User owner);
}
