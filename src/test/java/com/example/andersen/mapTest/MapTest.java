package com.example.andersen.mapTest;

import com.example.andersen.map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    Map<Integer, String> myMap = new Map<>();

    @BeforeEach
    public void init() {
        myMap.put(1, "Java");
        myMap.put(-5, "ABC");
        myMap.put(15, "GHL");
    }

    @Test
    public void getValueByKeyTest() {
        assertThat(myMap.get(1)).isEqualTo("Java");
    }

    @Test
    public void putTest() {
        myMap.put(20, "GHL");
        assertThat(myMap.size()).isEqualTo(4);
    }

    @Test
    public void size() {
        assertThat(myMap.size()).isEqualTo(3);
    }

    @Test
    public void removeTest() {
        myMap.remove(15);
        assertThat(myMap.size()).isEqualTo(2);

    }

    @Test
    public void keySetTest() {
        Set<Integer> expected=new HashSet<>();
        expected.add(15);
        expected.add(1);
        expected.add(-5);
        Set<Integer> actual = myMap.keySet();
        assertEquals(expected, actual);
    }
}
