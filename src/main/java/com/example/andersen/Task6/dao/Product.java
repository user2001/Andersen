package com.example.andersen.Task6.dao;

import com.example.andersen.Task6.currency.Currency;
import com.example.andersen.Task6.currency.CurrencyName;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private Currency currency;
    private final double UA_SELL_PERCENT = 1.2;
    private final double NON_UA_PRODUCT_SELL_PERCENT = 1.8;


    public BigDecimal sell_price(BigDecimal price) {
        if (!getCurrency().getCurrency_name().equals(CurrencyName.UAH)) {
            price = price.multiply(BigDecimal.valueOf(getCurrency().getExchangeRateIntoUAH() * NON_UA_PRODUCT_SELL_PERCENT));
        } else {
            price = price.multiply(BigDecimal.valueOf(getCurrency().getExchangeRateIntoUAH() * UA_SELL_PERCENT));
        }
        return price;
    }

}
