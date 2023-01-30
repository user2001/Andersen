package com.example.andersen.Task7.currency;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class CurrencyResolver {
    private static final Map<Currency, BigDecimal> currencyMap=new HashMap<>();
    static {
        currencyMap.put(Currency.UAH,BigDecimal.valueOf(1));
        currencyMap.put(Currency.EURO,BigDecimal.valueOf(40.2));
        currencyMap.put(Currency.USD,BigDecimal.valueOf(38.4));
    }
    public static BigDecimal exchangeIntoUAH(Currency currency, BigDecimal amount){
        return currencyMap.get(currency).multiply(amount);
    }
}
