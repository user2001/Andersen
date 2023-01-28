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
public class NotFood extends Product {
    private LocalDate createAtDate;

    public NotFood(int id, String name, BigDecimal price, Currency currency, LocalDate createAtDate) {
        super(id, name, price, currency);
        this.createAtDate = createAtDate;
    }

    @Override
    public String toString() {
        return "NotFood{ id=" + getId() + ", name=" + getName() + ", price=" + getPrice() +" "+getCurrency()+
                " , createAtDate=" + createAtDate + '}';
    }
}
