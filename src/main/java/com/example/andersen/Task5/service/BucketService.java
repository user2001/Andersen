package com.example.andersen.Task5.service;

import com.example.andersen.Task5.dao.Product;
import com.example.andersen.Task5.exception.PutWrongNumberException;

import java.util.*;

import static com.example.andersen.Task5.service.MenuServiceImpl.init;

public class BucketService {
    private HashMap<Product, Integer> orders = new HashMap<>();

    public void addProduct(int product_id, int addAmount) {
        Product product = findProductById(product_id).
                orElseThrow(() -> new PutWrongNumberException("Product with id: " + product_id +
                        " doesn't exist. Try again"));
        if (orders.containsKey(product)) {
            int oldValue = orders.get(product);
            orders.replace(product, oldValue + addAmount);
        } else {
            orders.put(product, addAmount);
        }
        showBucket();
    }

    public void deleteProduct(int product_id, int deleteAmount) {
        Product product = findProductById(product_id).
                orElseThrow(() -> new PutWrongNumberException("Product with id: " + product_id + "doesn't exist"));
        int oldValue = orders.get(product);
        int newValue = oldValue - deleteAmount;
        if (newValue > 0) {
            orders.replace(product, oldValue - deleteAmount);
        } else {
            orders.remove(product);
        }
        showBucket();
    }

    public void showBucket() {
        if (orders.size() > 0) {
            System.out.println("Your bucket");
            for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
                System.out.println(entry.getKey() + ", how many: " + entry.getValue());
            }
        } else {
            System.out.println("Your bucket is empty now");
        }
    }

    private Optional<Product> findProductById(int product_id) {
        List<Product> dataOfProducts = init();
        for (Product dataOfProduct : dataOfProducts) {
            int wantedProductId = dataOfProduct.getId();
            if (wantedProductId == product_id) {
                return Optional.ofNullable(dataOfProduct);
            }
        }
        throw new PutWrongNumberException("Product with id: " + product_id + "doesn't exist");
    }

    public void clearBucket() {
        orders.clear();
    }
}
