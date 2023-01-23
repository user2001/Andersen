package com.example.andersen.treesetTest;

import com.example.andersen.Task2.treeset.MyTreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MyTreeSetTest {
    MyTreeSet<Integer> myTreeSet = new MyTreeSet();

    @BeforeEach
    public void init() {
        myTreeSet.insert(1);
        myTreeSet.insert(-5);
        myTreeSet.insert(15);
    }

    @Test
    public void insertTest() {
        Assertions.assertTrue(myTreeSet.insert(5));
        Assertions.assertFalse(myTreeSet.insert(5));
    }

    @Test
    public void insertThrowsExceptionIfParameterIsNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> myTreeSet.insert(null));
    }

    @Test
    public void containTest() {
        assertThat(myTreeSet.containsValue(-5)).isTrue();
        assertThat(myTreeSet.containsValue(15)).isTrue();
    }

    @Test
    void containsReturnsFalseIfElementDoesntExist() {
        assertThat(myTreeSet.containsValue(100)).isFalse();
    }

    @Test
    public void containsThrowsExceptionIFParameterIsNull() {
        assertThatNullPointerException().isThrownBy(() -> myTreeSet.containsValue(null));
    }

    @Test
    public void clearTest() {
        myTreeSet.clear();
        assertEquals(myTreeSet.size(), 0);
    }

    @Test
    public void sizeTest() {
        assertEquals(myTreeSet.size(), 3);
    }

    @Test
    public void getFirstTest() {
        assertEquals(-5, myTreeSet.getFirst());
        assertNotEquals(1, myTreeSet.getFirst());
    }

    @Test
    public void getLastTest() {
        assertEquals(15, myTreeSet.getLast());
        assertNotEquals(-5, myTreeSet.getLast());
    }

}
