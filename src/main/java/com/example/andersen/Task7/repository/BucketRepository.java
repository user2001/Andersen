package com.example.andersen.Task7.repository;

import com.example.andersen.Task7.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, Integer> {
    @Query(value = "select id, owner_id from orders where owner_id = ?1 union " +
            "select id, owner_id from orders inner join order_product on id =order_id and " +
            "product_id = ?1", nativeQuery = true)
    List<Bucket> getByUserId(int userId);
}
