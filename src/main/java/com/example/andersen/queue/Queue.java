package com.example.andersen.queue;

import java.util.NoSuchElementException;

public class Queue {
    private int[] arr;
    private int front;
    private int rear;
    private int capacity;
    private int count;

    Queue(int size) {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = 0;
        count = 0;

    }

    public int remove() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int removedElement = arr[front];

        front = (front + 1) % capacity;
        count--;

        return removedElement;
    }

    public boolean add(int item) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        rear++;
        return true;
    }

    public boolean isFull() {
        return (size() == capacity);
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return count;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[front];
    }

}
