package com.example.andersen.Task6.currency;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@ToString
@AllArgsConstructor
public class Currency implements Serializable {
    private String international_code;
    private CurrencyName currency_name;
    private double exchangeRateIntoUAH;

    public String getInternational_code() {
        return international_code;
    }

    public double getExchangeRateIntoUAH() {
        return exchangeRateIntoUAH;
    }

    public CurrencyName getCurrency_name() {
        return currency_name;
    }
}
