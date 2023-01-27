package com.example.andersen.Task5.dao;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NotFood extends Product{
    private LocalDate createAtDate;

    public NotFood(int id, String name, BigDecimal price, LocalDate createAtDate) {
        super(id, name, price);
        this.createAtDate = createAtDate;
    }
    @Override
    public String toString() {
        return "NotFood{ id=" +getId()+", name="+getName()+", price="+getPrice()+
                " , createAtDate=" + createAtDate +
                '}';
    }
}
