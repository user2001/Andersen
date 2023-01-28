package com.example.andersen.Task6.dao;

import com.example.andersen.Task6.currency.Currency;
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
    @ExpirationDate
    private LocalDate expirationDate;

    public Food(int id, String name, BigDecimal price, Currency currency,LocalDate expirationDate) {
        super(id, name, price, currency);
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Food{ id=" +getId()+", name="+getName()+", price="+ getPrice()+
                " , expirationDate=" + expirationDate +
                '}';
    }
}
