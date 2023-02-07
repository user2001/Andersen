package com.example.task9_securityexpand.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class ProductDto {
    private int id;
    private String name;
    private boolean isFood;
    private BigDecimal price;
    private String currency;
    private LocalDate manufactureDate;

}
