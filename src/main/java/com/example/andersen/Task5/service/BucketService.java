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

    public void deleteProduct(long product_id) {

    }

    public void showBucket() {
        System.out.println("Your bucket");
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            System.out.println(entry.getKey() + ", how many: " + entry.getValue());
        }
    }

    //    private Optional<Product> findProductById(int product_Id) {
//        List<Product> dataOfProducts = init();
//        int idOfNeededProduct = dataOfProducts.stream().
//                map(Product::getId).
//                filter(id -> id.equals(product_Id)).
//                findFirst().get();
//        Optional<Product> wanted = Optional.ofNullable(dataOfProducts.get(idOfNeededProduct));
//        return wanted;
//    }
    private Optional<Product> findProductById(int product_Id) {
        List<Product> dataOfProducts = init();
        for (Product dataOfProduct : dataOfProducts) {
            int wantedProductId = dataOfProduct.getId();
            if (wantedProductId == product_Id) {
                return Optional.ofNullable(dataOfProduct);
            }
        }
        throw  new NoSuchElementException();
    }
}
