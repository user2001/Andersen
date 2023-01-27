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
public class NotFood extends Product {
    private LocalDate createAtDate;

    public NotFood(int id, String name, BigDecimal bought_price, BigDecimal sell_price, Currency currency, LocalDate createAtDate) {
        super(id, name, bought_price, sell_price, currency);
        this.createAtDate = createAtDate;
    }

    @Override
    public String toString() {
        return "NotFood{ id=" + getId() + ", name=" + getName() + ", price=" + getBought_price() +
                " , createAtDate=" + createAtDate +
                '}';
    }
}
