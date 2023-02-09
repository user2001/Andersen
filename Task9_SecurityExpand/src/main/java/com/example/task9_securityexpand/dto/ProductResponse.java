package com.example.task9_securityexpand.dto;

import com.example.task9_securityexpand.currency.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private Currency currency;
    private BigDecimal price;
}
