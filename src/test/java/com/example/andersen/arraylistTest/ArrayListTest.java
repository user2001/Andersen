package com.example.andersen.arraylistTest;

import com.example.andersen.arraylist.ArrayList;
import com.example.andersen.arraylist.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayListTest {
    private List<Integer> arrayList = new ArrayList<>();

    @BeforeEach
    public void init() {
        arrayList.add(5);
        arrayList.add(8);
        arrayList.add(0);
    }

    @Test
    public void addTest() {
        arrayList.add(5);
        arrayList.add(8);
        arrayList.add(0);
        assertEquals(6, arrayList.size());
    }

    @Test
    public void addByIndexTest() {
        arrayList.add(3, 1000);
        assertEquals(4, arrayList.size());

    }

    @Test
    void addElementByIndexLargerThanListSize() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.add(5, 88));
    }

    @Test
    public void setTest() {
        arrayList.set(2, 5000);
        assertEquals(5000, arrayList.get(2));
    }

    @Test
    public void getFirstTest() {
        assertEquals(5, arrayList.getFirst());
    }

    @Test
    public void getLastTest() {
        assertEquals(0, arrayList.getLast());
    }

    @Test
    void getElementByIndexThrowsExceptionWhenIndexIsOutOfBound() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.get(4));
    }

    @Test
    public void sizeTest() {
        assertEquals(3, arrayList.size());
    }

    @Test
    public void isEmptyTest() {
        assertThat(arrayList.isEmpty()).isFalse();
    }

    @Test
    public void clearTest() {
        arrayList.clear();
        assertEquals(0, arrayList.size());
    }

    @Test
    public void containsTest() {
        assertTrue(arrayList.contains(5));
    }

    @Test
    public void containsReturnFalseIfParameterDosentExist() {
        assertThat(arrayList.contains(1000)).isFalse();
    }

    @Test
    void removeElementByIndexThrowsExceptionWhenIndexIsOutOfBounds() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> arrayList.remove(6));
    }

    @Test
    void removeElementByIndex() {
        assertThat(arrayList.remove(0)).isEqualTo(5);
    }
}
