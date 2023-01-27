package com.example.andersen.Task6.dao;

import com.example.andersen.Task6.Currency;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Food extends Product {
    private LocalDate expirationDate;

    public Food(int id, String name, BigDecimal bought_price, BigDecimal sell_price, Currency currency,LocalDate expirationDate) {
        super(id, name, bought_price, sell_price, currency);
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Food{ id=" +getId()+", name="+getName()+", price="+getBought_price()+
                " , expirationDate=" + expirationDate +
                '}';
    }
}
