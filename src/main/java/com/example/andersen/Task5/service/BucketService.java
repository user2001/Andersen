package com.example.andersen.Task5.service;

import com.example.andersen.Task5.dao.Product;

import java.util.*;

import static com.example.andersen.Task5.service.MenuServiceImpl.init;

public class BucketService {
    private HashMap<Product, Integer> orders = new HashMap<>();

    public void addProduct(int product_id, int count) {
        Product product = findProductById(product_id).orElseThrow(() -> new NoSuchElementException());
        orders.put(product, count);
        showBucket();
    }

    public void deleteProduct(int product_id) {
        Product product = findProductById(product_id).orElseThrow(() -> new NoSuchElementException());
        orders.remove(product);
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

    private Optional<Product> findProductById(int product_Id) {
        List<Product> dataOfProducts = init();
        for (Product dataOfProduct : dataOfProducts) {
            int wantedProductId = dataOfProduct.getId();
            if (wantedProductId == product_Id) {
                return Optional.ofNullable(dataOfProduct);
            }
        }
        throw new NoSuchElementException();
    }

    public void clearBucket() {
        orders.clear();
    }
}
