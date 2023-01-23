package com.example.andersen.treemapTest;

import com.example.andersen.Task2.treemap.MyTreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTreeMapTest {
    MyTreeMap<Integer, String> myTreeMap = new MyTreeMap<>();

    @BeforeEach
    public void init() {
        myTreeMap.insert(1, "Java");
        myTreeMap.insert(-5, "ABC");
        myTreeMap.insert(15, "GHL");
    }

    @Test
    public void insertTest() {
        Assertions.assertTrue(myTreeMap.insert(2, "Java"));
        Assertions.assertFalse(myTreeMap.insert(2, "Python"));
    }
    @Test
    public void insertThrowsExceptionIfParameterIsNull() {
        assertThatIllegalArgumentException().isThrownBy(() ->myTreeMap.insert(null,"test"));
    }

    @Test
    public void getFirstKeyTest() {
        assertEquals(-5, myTreeMap.getFirstKey());
        Assertions.assertNotEquals(1, myTreeMap.getFirstKey());
    }

    @Test
    public void getLastKeyTest() {
        assertEquals(15, myTreeMap.getLastKey());
        Assertions.assertNotEquals(-5, myTreeMap.getLastKey());
    }

    @Test
    public void containValueTest() {
        assertThat(myTreeMap.containsValue("Java")).isTrue();
        assertThat(myTreeMap.containsValue("ABC")).isTrue();

    }

    @Test
    void containsReturnsFalseIfElementDoesntExist() {
        assertThat(myTreeMap.containsValue("100")).isFalse();
    }

    @Test
    public void containsThrowsExceptionIFParameterIsNull() {
        assertThatNullPointerException().isThrownBy(() -> myTreeMap.containsValue(null));
    }

    @Test
    public void getValueByKeyTest() {
        assertEquals("Java", myTreeMap.getValueByKey(1));
    }

    @Test
    public void getValueByKeyReturnsNullIfElementDoesntExist() {
        assertThat(myTreeMap.getValueByKey(100)).isNull();
    }

    @Test
    public void getValueByKeyThrowsExceptionIFParameterIsNull() {
        assertThatNullPointerException().isThrownBy(() -> myTreeMap.getValueByKey(null));
    }

    @Test
    public void clearTest() {
        myTreeMap.clear();
        assertEquals(myTreeMap.size(), 0);
    }

    @Test
    public void sizeTest() {
        assertEquals(myTreeMap.size(), 3);
    }

    @Test
    public void keySetTest() {
        Set<Integer> expected = new LinkedHashSet<>();
        expected.add(15);
        expected.add(1);
        expected.add(-5);
        Set<Integer> actual = myTreeMap.keySet();
        assertEquals(expected, actual);

    }
}
