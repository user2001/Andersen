package com.example.andersen.Task7.model;

import com.example.andersen.Task7.currency.Currency;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal price;
    private boolean isFood;
    private int expirationDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDate manufactureDate;
    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orderList;

}
