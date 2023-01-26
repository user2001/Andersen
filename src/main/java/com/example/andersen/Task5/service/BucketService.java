package com.example.andersen.Task5.service;

import com.example.andersen.Task5.dao.Product;
import com.example.andersen.Task5.exception.PutWrongNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.example.andersen.Task5.service.MenuServiceImpl.init;

public class BucketService {
    private HashMap<Product, Integer> orders = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(BucketService.class);

    public void addProduct(int product_id, int addAmount) {
        Product product = findProductById(product_id);

        if (product == null) {
            logger.error("Product id:" + product_id + " doesn't exist");
            throw new PutWrongNumberException("Product with id: " + product_id +
                    " doesn't exist. Try again");
        } else {
            if (orders.containsKey(product)) {
                logger.info("product already exist in bucket");
                int oldValue = orders.get(product);
                orders.replace(product, oldValue + addAmount);
                logger.info("adding amount:" + addAmount + " of product id:" + product_id + " to the bucket");
            } else {
                orders.put(product, addAmount);
                logger.info("product with id:" + product_id + " in amount:" + addAmount + " was added to the bucket");
            }
            showBucket();
        }
    }

    public boolean deleteProduct(int product_id, int deleteAmount) {
        Product product = findProductById(product_id);

        if (product == null) {
            logger.error("Product id:" + product_id + " doesn't exist");
            throw new PutWrongNumberException("Product with id: " + product_id +
                    " doesn't exist. Try again");
        } else {
            int oldValue = orders.get(product);
            int newValue = oldValue - deleteAmount;
            if (newValue > 0) {
                logger.info("removing " + deleteAmount + " products from bucket with id:" + product_id);
                orders.replace(product, oldValue - deleteAmount);
            } else {
                logger.info("removing all products with id:" + product_id + " from bucket");
                orders.remove(product);
            }
            showBucket();
            return true;
        }
    }

    private void showBucket() {
        if (orders.size() > 0) {
            System.out.println("YOUR BUCKET:");
            for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
                System.out.println(entry.getKey() + ", how many: " + entry.getValue());
            }
        } else {
            System.out.println("Your bucket is empty now");
        }
    }

    private Product findProductById(int product_id) {
        List<Product> dataOfProducts = init();
        for (Product dataOfProduct : dataOfProducts) {
            int wantedProductId = dataOfProduct.getId();
            if (wantedProductId == product_id) {
                return dataOfProduct;
            }
        }
        return null;
    }

    public void clearBucket() {
        orders.clear();
    }
    public int getBucketSize(){
        return orders.size();
    }
}
