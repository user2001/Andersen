package com.example.andersen.Task7.repository;

import com.example.andersen.Task7.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
