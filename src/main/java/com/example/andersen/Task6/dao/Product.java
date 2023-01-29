package com.example.andersen.Task6.dao;

import com.example.andersen.Task6.currency.Currency;
import com.example.andersen.Task6.currency.CurrencyName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Product implements Serializable {
    private int id;

    private String name;
    private BigDecimal price;

    private Currency currency;
    @ExpirationDate
    private int expirationDate;

    private LocalDate createAtDate;
    private final double UA_SELL_PERCENT = 1.2;
    private final double NON_UA_PRODUCT_SELL_PERCENT = 1.8;


    public BigDecimal sell_price() {
        if (!getCurrency().getCurrency_name().equals(CurrencyName.UAH)) {
            price = price.multiply(BigDecimal.valueOf(getCurrency().getExchangeRateIntoUAH() * NON_UA_PRODUCT_SELL_PERCENT));
        } else {
            price = price.multiply(BigDecimal.valueOf(getCurrency().getExchangeRateIntoUAH() * UA_SELL_PERCENT));
        }
        return price;
    }


}
