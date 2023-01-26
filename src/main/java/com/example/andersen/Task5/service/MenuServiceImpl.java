package com.example.andersen.Task5.service;

import com.example.andersen.Task5.dao.Food;
import com.example.andersen.Task5.dao.NotFood;
import com.example.andersen.Task5.dao.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {
    Scanner scanner = new Scanner(System.in);
    BucketService bucket = new BucketService();
    Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    public static List<Product> init() {

        List<Product> shop = new ArrayList<>();

        Food bread = new Food(8, "Baton", BigDecimal.valueOf(20), LocalDate.of(2023, 1, 30));
        Food milk = new Food(5, "Milk", BigDecimal.valueOf(40), LocalDate.of(2023, 2, 5));
        Food tomato = new Food(10, "Tomato", BigDecimal.valueOf(20), LocalDate.of(2023, 2, 10));

        NotFood phone = new NotFood(7, "Phone", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        NotFood lamp = new NotFood(4, "Lamp", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        NotFood battery = new NotFood(2, "EcoFlow", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        Collections.addAll(shop, bread, milk, tomato, phone, lamp, battery);
        return shop;
    }

    @Override
    public void showListOfProducts() {
        logger.info("showing list of products");
        System.out.println("LIST OF PRODUCTS:");
        List<Product> productList = init();
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Override
    public void addProductToTheBucket() {
        logger.info("adding product to the bucket");
        System.out.print("Put id of chosen product: ");
        int chosen_productId = scanner.nextInt();
        System.out.println();
        System.out.print("Quantity of product you want order: ");
        int quantityOfProduct = scanner.nextInt();
        bucket.addProduct(chosen_productId, quantityOfProduct);
    }

    @Override
    public void deleteProductFromBucket() {
        logger.info("deleting product");
        System.out.print("Put id fo product you want to delete: ");
        int delete_ProductId = scanner.nextInt();
        System.out.print("Quantity of product you want to delete: ");
        int quantityOfProduct = scanner.nextInt();
        bucket.deleteProduct(delete_ProductId, quantityOfProduct);
    }

    @Override
    public void clearTheBucket() {
        bucket.clearBucket();
        System.out.println("Bucket is cleared");
    }
}
