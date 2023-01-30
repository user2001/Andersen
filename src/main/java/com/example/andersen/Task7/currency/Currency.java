package com.example.andersen.Task7.currency;

public enum Currency {
    UAH("uah"),USD("usd"),EURO("euro");
    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
