package com.example.andersen.Task7.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
public class Info implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate orderCreatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    private Order order;
    private boolean processed;
    private String info;
}
