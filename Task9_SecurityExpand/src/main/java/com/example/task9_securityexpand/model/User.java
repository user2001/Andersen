package com.example.task9_securityexpand.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Order> orderList;
}
