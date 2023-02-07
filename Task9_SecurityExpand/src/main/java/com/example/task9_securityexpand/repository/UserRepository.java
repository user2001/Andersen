package com.example.task9_securityexpand.repository;


import com.example.task9_securityexpand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
