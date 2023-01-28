package com.example.andersen.Task6.warehouse;


import com.example.andersen.Task6.currency.CurrencyName;
import com.example.andersen.Task6.currency.Currency;
import com.example.andersen.Task6.dao.Food;
import com.example.andersen.Task6.dao.NotFood;
import com.example.andersen.Task6.dao.Product;
import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Warehouse {
    private static Map<Product, Integer> allProducts;

    @PostConstruct
    public Map<Product, Integer> init() {

        Currency uah = new Currency("Ukraine", CurrencyName.UAH, 1);
        Currency euro = new Currency("Spain", CurrencyName.EURO, 40.2);

        allProducts = new HashMap<>();

        Food bread = new Food(8, "Baton", BigDecimal.valueOf(20), uah, LocalDate.of(2023, 1, 30));
        Food milk = new Food(5, "Milk", BigDecimal.valueOf(40), uah, LocalDate.of(2023, 2, 5));
        Food tomato = new Food(10, "Tomato", BigDecimal.valueOf(3), euro, LocalDate.of(2023, 2, 10));

        NotFood phone = new NotFood(7, "Phone", BigDecimal.valueOf(1000), euro, LocalDate.of(2022, 10, 15));
        NotFood lamp = new NotFood(4, "Lamp", BigDecimal.valueOf(1000), uah, LocalDate.of(2022, 10, 15));
        NotFood battery = new NotFood(2, "EcoFlow", BigDecimal.valueOf(200), euro, LocalDate.of(2022, 10, 15));

        allProducts.put(bread, 100);
        allProducts.put(milk, 200);
        allProducts.put(tomato, 50);
        allProducts.put(phone, 1000);
        allProducts.put(lamp, 100);
        allProducts.put(battery, 80);

        return allProducts;
    }

    public boolean deleteProductFromWarehouse(Product product, int deleteAmount) {
        return true;
    }

    public Map<Product, Integer> getAllProducts() {
        return allProducts;
    }
}
