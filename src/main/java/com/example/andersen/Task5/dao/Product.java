package com.example.andersen.Task5.dao;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@ToString
public class Product {
    private long id;
    private String name;
    private BigDecimal price;
}
