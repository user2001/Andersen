package com.example.andersen.Task6.bucket;

import com.example.andersen.Task5.exception.PutWrongNumberException;
import com.example.andersen.Task5.service.BucketService;
import com.example.andersen.Task6.dao.Product;
import com.example.andersen.Task6.warehouse.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BucketImpl implements Bucket {
    private Map<Product, Integer> orders = new HashMap<>();
    private Warehouse warehouse = new Warehouse();
    Logger logger = LoggerFactory.getLogger(BucketService.class);

    public boolean isEnoughAmountOfProduct(int product_id, int wanted_amount) {
        int existAmount = warehouse.getAllProducts().get(findProductById(product_id));
        return existAmount >= wanted_amount;
    }

    @Override
    public void addProduct(int product_id, int addAmount) {
        if (addAmount <= 0 || !isEnoughAmountOfProduct(product_id, addAmount)) {
            logger.error("Invalid amount of product");
            throw new PutWrongNumberException();
        }
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
                warehouse.deleteProductFromWarehouse(product, addAmount);
                logger.info("adding amount:" + addAmount + " of product id:" + product_id + " to the bucket");
            } else {
                orders.put(product, addAmount);
                warehouse.deleteProductFromWarehouse(product, addAmount);
                logger.info("product with id:" + product_id + " in amount:" + addAmount + " was added to the bucket");
            }
            showBucket();
        }

    }

    @Override
    public boolean deleteProduct(int product_id, int deleteAmount) {
        if (deleteAmount <= 0) {
            logger.error("Invalid amount of product");
            throw new PutWrongNumberException();
        }

        Product product = findProductById(product_id);

        if (product == null) {
            logger.error("Product id:" + product_id + " doesn't exist");
            throw new PutWrongNumberException(String.valueOf(product_id));
        } else {
            int oldValue = orders.get(product);
            int newValue = oldValue - deleteAmount;
            if (newValue > 0) {
                logger.info("removing " + deleteAmount + " products from bucket with id:" + product_id);
                orders.replace(product, oldValue - deleteAmount);
                showBucket();
                return true;
            } else if (newValue < 0) {
                logger.error("Invalid amount of product");
                throw new PutWrongNumberException();
            } else {
                logger.info("removing all products with id:" + product_id + " from bucket");
                orders.remove(product);
                showBucket();
                return true;

            }

        }
    }

    private Product findProductById(int product_id) {
        Set<Product> dataOfProducts = warehouse.getAllProducts().keySet();
        for (Product dataOfProduct : dataOfProducts) {
            int wantedProductId = dataOfProduct.getId();
            if (wantedProductId == product_id) {
                return dataOfProduct;
            }
        }
        return null;
    }

    @Override
    public void clearBucket() {
        logger.info("clearing bucket");
        orders.clear();
    }

    public void showBucket() {
        if (orders.size() > 0) {
            System.out.println("YOUR BUCKET:");
            for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
                System.out.println(entry.getKey() + ", how many: " + entry.getValue());
            }
        } else {
            System.out.println("Your bucket is empty now");
        }
    }

    @Override
    public BigDecimal total_cost() {
        return null;
    }

    @Override
    public boolean saveToFile(String fileName) {
        boolean result = true;

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(orders);
        } catch (IOException e) {
            result = false;
        }

        return result;
    }

    @Override
    public boolean loadFile(String fileName) {
        boolean result = true;

        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            orders = (Map<Product, Integer>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            result = false;
        }

        return result;
    }
}
