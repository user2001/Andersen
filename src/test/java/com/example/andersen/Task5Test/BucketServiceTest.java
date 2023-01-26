package com.example.andersen.Task5Test;

import com.example.andersen.Task5.dao.Food;
import com.example.andersen.Task5.dao.NotFood;
import com.example.andersen.Task5.dao.Product;
import com.example.andersen.Task5.exception.PutWrongNumberException;
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

import static org.junit.jupiter.api.Assertions.*;


public class BucketServiceTest {
    BucketService bucketService = new BucketService();
    List<Product> shopTest;

    @BeforeEach
    public void initTestData() {

        shopTest = new ArrayList<>();

        Food bread = new Food(8, "Baton", BigDecimal.valueOf(20), LocalDate.of(2023, 1, 30));
        Food milk = new Food(5, "Milk", BigDecimal.valueOf(40), LocalDate.of(2023, 2, 5));
        Food tomato = new Food(10, "Tomato", BigDecimal.valueOf(20), LocalDate.of(2023, 2, 10));

        NotFood phone = new NotFood(7, "Phone", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        NotFood lamp = new NotFood(4, "Lamp", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));
        NotFood battery = new NotFood(2, "Ecoflow", BigDecimal.valueOf(1000), LocalDate.of(2022, 10, 15));

        Collections.addAll(shopTest, bread, milk, tomato, phone, lamp, battery);

        bucketService.addProduct(8, 2);

    }

    @Test
    public void addValidProductTest() {
        bucketService.addProduct(8, 2);
        bucketService.addProduct(5, 1);
        assertEquals(2, bucketService.getBucketSize());
    }

    @Test
    public void throwExceptionWhenAddInValidProductTest() {
        assertThrows(PutWrongNumberException.class, () -> bucketService.addProduct(100, 5));
        assertThrows(PutWrongNumberException.class, () -> bucketService.addProduct(8, -5));
        assertThrows(PutWrongNumberException.class, () -> bucketService.addProduct(8, 0));
    }

    @Test
    public void deleteValidProductTest() {
        assertTrue(bucketService.deleteProduct(8, 2));
    }

    @Test
    public void throwExceptionWhenDeleteInValidProductTest() {
        assertThrows(PutWrongNumberException.class, () -> bucketService.deleteProduct(8, 5));
        assertThrows(PutWrongNumberException.class, () -> bucketService.deleteProduct(8, -5));
        assertThrows(PutWrongNumberException.class, () -> bucketService.deleteProduct(8, 0));
    }

    @Test
    public void clearBucketTest() {
        bucketService.clearBucket();
        assertEquals(0, bucketService.getBucketSize());
    }
}
