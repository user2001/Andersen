package com.example.andersen.Task5.dao;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Food extends Product{
    private LocalDate expirationDate;

    public Food(int id, String name, BigDecimal price,LocalDate expirationDate) {
        super(id, name, price);
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Food{ id=" +getId()+", name="+getName()+", price="+getPrice()+
                " , expirationDate=" + expirationDate +
                '}';
    }
}
