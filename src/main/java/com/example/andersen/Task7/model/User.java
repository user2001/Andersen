package com.example.andersen.Task7.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Order> orderList;

}
