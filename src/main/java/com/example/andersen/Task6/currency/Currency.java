package com.example.andersen.Task6.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Currency implements Serializable {
    private String international_code;
    private CurrencyName currency_name;
    private double exchangeRateIntoUAH;

}
