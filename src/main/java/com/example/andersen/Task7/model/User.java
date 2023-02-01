package com.example.andersen.Task7.model;

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
    @Column(unique = true)
    private String email;
    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Order> orderList;
}
