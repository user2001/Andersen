package com.example.andersen.Task6.dao;

import com.example.andersen.Task6.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private BigDecimal bought_price;
    private BigDecimal sell_price;
    private Currency currency;
}
