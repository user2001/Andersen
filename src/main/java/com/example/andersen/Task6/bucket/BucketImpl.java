package com.example.andersen.Task6.bucket;

import com.example.andersen.Task5.exception.PutWrongNumberException;
import com.example.andersen.Task6.dao.Product;
import com.example.andersen.Task6.warehouse.Warehouse;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class BucketImpl implements Bucket {
    private final Warehouse warehouse = new Warehouse();
    private Map<Product, Integer> orders = new HashMap<>();

    public boolean isEnoughAmountOfProduct(int product_id, int wanted_amount) {
        Map<Product, Integer> map = warehouse.getAllProducts();
        Product wanted = findProductById(product_id);
        if (wanted == null) {
            throw new PutWrongNumberException(String.valueOf(product_id));
        }
        int existAmount = map.get(wanted);
        return existAmount >= wanted_amount;
    }

    @Override
    public void addProduct(int product_id, int addAmount) {
        if (addAmount <= 0 || !isEnoughAmountOfProduct(product_id, addAmount)) {
            throw new PutWrongNumberException();
        }
        Product product = findProductById(product_id);

        if (product == null) {
            throw new PutWrongNumberException("Product with id: " + product_id +
                    " doesn't exist");
        } else {
            if (orders.containsKey(product)) {
                int oldValue = orders.get(product);
                orders.replace(product, oldValue + addAmount);
                warehouse.deleteProductFromWarehouse(product, addAmount);
            } else {
                orders.put(product, addAmount);
                warehouse.deleteProductFromWarehouse(product, addAmount);
            }
            showBucket();
        }
    }

    @Override
    public boolean deleteProduct(int product_id, int deleteAmount) {
        if (deleteAmount <= 0) {
            throw new PutWrongNumberException();
        }

        Product product = findProductById(product_id);

        if (product == null) {
            throw new PutWrongNumberException(String.valueOf(product_id));
        } else {
            int oldValue = orders.get(product);
            int newValue = oldValue - deleteAmount;
            if (newValue > 0) {
                orders.replace(product, oldValue - deleteAmount);
                warehouse.addProductToWarehouse(product, deleteAmount);
                showBucket();
                return true;
            } else if (newValue < 0) {
                throw new PutWrongNumberException();
            } else {
                orders.remove(product);
                warehouse.addProductToWarehouse(product, deleteAmount);
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
        orders.clear();
    }

    @Override
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

    public Map<Product, Integer> getOrder() {
        return orders;
    }

    @Override
    public BigDecimal total_cost() {
        BigDecimal total = BigDecimal.valueOf(0);
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            var priceProduct = entry.getKey().sell_price().multiply(BigDecimal.valueOf(entry.getValue()));
            total = total.add(priceProduct);
        }
        return total;
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
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
