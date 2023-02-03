package com.example.andersen.Task7.repository;

import com.example.andersen.Task7.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select id, owner_id,submit from orders where owner_id = ?1 union " +
            "select id, owner_id, submit from orders inner join order_product on id =order_id and " +
            "product_id = ?1", nativeQuery = true)
    List<Order> getByUserId(int userId);
}
