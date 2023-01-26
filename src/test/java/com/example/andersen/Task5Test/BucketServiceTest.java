package com.example.andersen.Task5Test;

import com.example.andersen.Task5.dao.Food;
import com.example.andersen.Task5.dao.NotFood;
import com.example.andersen.Task5.dao.Product;
import com.example.andersen.Task5.service.BucketService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BucketServiceTest {
    BucketService bucketService;
    @BeforeEach
    public void initTestData(){
        bucketService=new BucketService();
        List<Product> shopTest = new ArrayList<>();

        Food bread = new Food(8, "Baton", BigDecimal.valueOf(20), LocalDate.of(2023, 1, 30));
        Food milk = new Food(5, "Milk", BigDecimal.valueOf(40), LocalDate.of(2023, 2, 5));
        Food tomato = new Food(10, "Tomato", BigDecimal.valueOf(20), LocalDate.of(2023, 2, 10));

        NotFood phone = new NotFood(7, "Phone", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        NotFood lamp = new NotFood(4, "Lamp", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        NotFood battery = new NotFood(2, "Ecoflow", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        Collections.addAll(shopTest, bread, milk, tomato, phone, lamp, battery);
    }

    @Test
    public void addProductTest(){
       bucketService.addProduct(7,2);
       assertEquals(bucketService.getBucketSize(),1);
    }

}
