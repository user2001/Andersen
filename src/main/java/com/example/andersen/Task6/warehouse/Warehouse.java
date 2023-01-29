package com.example.andersen.Task6.warehouse;


import com.example.andersen.Task5.exception.PutWrongNumberException;
import com.example.andersen.Task6.currency.Currency;
import com.example.andersen.Task6.currency.CurrencyName;
import com.example.andersen.Task6.dao.ExpirationDate;
import com.example.andersen.Task6.dao.Food;
import com.example.andersen.Task6.dao.NotFood;
import com.example.andersen.Task6.dao.Product;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Map<Product, Integer> allProducts;

    public Warehouse() {
        init();
    }

    private Map<Product, Integer> init() {

        Currency uah = new Currency("Ukraine", CurrencyName.UAH, 1);
        Currency euro = new Currency("Spain", CurrencyName.EURO, 40.2);

        allProducts = new HashMap<>();

        Food bread = new Food();
        bread.setId(8);
        bread.setName("Baton");
        bread.setPrice(BigDecimal.valueOf(20));
        bread.setCurrency(uah);
        bread.setCreateAtDate(LocalDate.of(2023, 1, 30));

        Food milk = new Food();
        milk.setId(5);
        milk.setName("Milk 2.5%");
        milk.setPrice(BigDecimal.valueOf(40));
        milk.setCurrency(uah);
        milk.setCreateAtDate(LocalDate.of(2023, 2, 5));

        Food tomato = new Food();
        tomato.setId(10);
        tomato.setName("Cherry tomato");
        tomato.setPrice(BigDecimal.valueOf(3));
        tomato.setCurrency(euro);
        tomato.setCreateAtDate(LocalDate.of(2023, 2, 10));


        NotFood phone = new NotFood();
        phone.setId(7);
        phone.setName("Iphone");
        phone.setPrice(BigDecimal.valueOf(1000));
        phone.setCurrency(euro);
        phone.setCreateAtDate(LocalDate.of(2022, 10, 15));

        NotFood lamp = new NotFood();
        lamp.setId(4);
        lamp.setName("Lamp");
        lamp.setPrice(BigDecimal.valueOf(1000));
        lamp.setCurrency(uah);
        lamp.setCreateAtDate(LocalDate.of(2022, 10, 15));

        NotFood battery = new NotFood();
        battery.setId(2);
        battery.setName("EcoFlow");
        battery.setPrice(BigDecimal.valueOf(200));
        battery.setCurrency(euro);
        battery.setCreateAtDate(LocalDate.of(2022, 10, 15));

        allProducts.put(bread, 100);
        allProducts.put(milk, 200);
        allProducts.put(tomato, 50);
        allProducts.put(phone, 1000);
        allProducts.put(lamp, 100);
        allProducts.put(battery, 80);

        return allProducts;
    }

    public void deleteProductFromWarehouse(Product product, int deleteAmount) {

        if (deleteAmount <= 0) {
            throw new PutWrongNumberException();
        }
        if (product == null) {
            throw new PutWrongNumberException(String.valueOf(product.getId()));
        } else {
            int oldValue = allProducts.get(product);
            int newValue = oldValue - deleteAmount;
            if (newValue > 0) {
                allProducts.replace(product, oldValue - deleteAmount);
            } else if (newValue < 0) {
                throw new PutWrongNumberException();
            } else {
                allProducts.remove(product);
            }
        }
    }

    public void addProductToWarehouse(Product product, int addAmount) {

        if (addAmount <= 0 || product == null) {
            throw new PutWrongNumberException("Invalid data");
        } else {
            checkForExpiration(product);
            if (allProducts.containsKey(product)) {
                int oldValue = allProducts.get(product);
                allProducts.replace(product, oldValue + addAmount);
            } else {
                allProducts.put(product, addAmount);
            }
        }
    }

    private void checkForExpiration(Product product) {
        Field[] fields = product.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExpirationDate.class)) {
                field.setAccessible(true);
                try {
                    if (product instanceof NotFood) {
                        field.setInt(product, 730);
                    } else if (product instanceof Food) {
                        field.setInt(product, 7);
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }
    }

    public Map<Product, Integer> getAllProducts() {
        return allProducts;
    }


}