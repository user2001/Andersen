package com.example.andersen.Task2Test.queueTest;

import com.example.andersen.Task2.queue.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueueTest {
    private Queue queue = new Queue();

    @BeforeEach
    public void init() {
        queue.add(5);
        queue.add(8);
        queue.add(0);
    }

    @Test
    public void removeTest() {
        queue.remove();
        assertThat(queue.size()).isEqualTo(2);
    }

    @Test
    public void addTest() {
        queue.add(15);
        queue.add(20);
        assertThat(queue.size()).isEqualTo(5);
    }

    @Test
    public void isFullTest() {
        queue.add(15);
        queue.add(20);
        assertTrue(queue.isFull());
    }

    @Test
    public void isEmptyTest() {
        assertFalse(queue.isEmpty());
    }

    @Test
    public void sizeTest() {
        assertThat(queue.size()).isEqualTo(3);

    }

    @Test
    public void peekTest() {
        assertThat(queue.peek()).isEqualTo(5);
    }
}
