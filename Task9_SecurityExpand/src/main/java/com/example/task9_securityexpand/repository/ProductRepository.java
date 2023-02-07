package com.example.task9_securityexpand.repository;


import com.example.task9_securityexpand.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("from Order o where o.id = :orderId")
    List<Product> getByOrderId(long orderId);
}
