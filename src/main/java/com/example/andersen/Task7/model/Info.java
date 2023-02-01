package com.example.andersen.Task7.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class Info implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate orderCreatedAt;
    @OneToOne
    private Order order;
    private boolean processed;
}
