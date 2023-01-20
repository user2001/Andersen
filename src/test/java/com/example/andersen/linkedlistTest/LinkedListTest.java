package com.example.andersen.linkedlistTest;

import com.example.andersen.arraylist.List;
import com.example.andersen.linkedlist.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListTest {
    private List<Integer> linkedList = new LinkedList<>();

    @BeforeEach
    public void init() {
        linkedList.add(5);
        linkedList.add(8);
        linkedList.add(0);
    }

    @Test
    public void addTest() {
        linkedList.add(5);
        linkedList.add(8);
        linkedList.add(0);
        assertEquals(6, linkedList.size());
    }

    @Test
    public void addByIndexTest() {
        linkedList.add(3, 1000);
        assertEquals(4, linkedList.size());

    }

    @Test
    void addElementByIndexLargerThanListSize() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> linkedList.add(5, 88));
    }

    @Test
    public void setTest() {
        linkedList.set(2, 5000);
        assertEquals(5000, linkedList.get(2));
    }

    @Test
    public void getFirstTest() {
        assertEquals(5, linkedList.getFirst());
    }

    @Test
    public void getLastTest() {
        assertEquals(0, linkedList.getLast());
    }

    @Test
    void getElementByIndexThrowsExceptionWhenIndexIsOutOfBound() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> linkedList.get(4));
    }

    @Test
    public void sizeTest() {
        assertEquals(3, linkedList.size());
    }

    @Test
    public void isEmptyTest() {
        assertThat(linkedList.isEmpty()).isFalse();
    }

    @Test
    public void clearTest() {
        linkedList.clear();
        assertEquals(0, linkedList.size());
    }

    @Test
    public void containsTest() {
        assertTrue(linkedList.contains(5));
    }

    @Test
    public void containsReturnFalseIfParameterDosentExist() {
        assertThat(linkedList.contains(1000)).isFalse();
    }

    @Test
    void removeElementByIndexThrowsExceptionWhenIndexIsOutOfBounds() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> linkedList.remove(6));
    }

    @Test
    void removeElementByIndex() {
        assertThat(linkedList.remove(0)).isEqualTo(5);
    }
}
