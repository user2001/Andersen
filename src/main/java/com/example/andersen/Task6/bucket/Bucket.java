package com.example.andersen.Task6.bucket;

import com.example.andersen.Task6.dao.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface Bucket {
    void addProduct(int product_id, int addAmount);

    boolean deleteProduct(int product_id, int deleteAmount);

    void clearBucket();

    BigDecimal total_cost();

    boolean saveToFile(String fileName);

    boolean loadFile(String fileName);


}
