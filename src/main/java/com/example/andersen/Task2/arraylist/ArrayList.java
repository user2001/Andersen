package com.example.andersen.Task2.arraylist;

import lombok.extern.log4j.Log4j;

import java.util.Objects;
import java.util.stream.Stream;

@Log4j
public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = new Object[initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T element) {
        log.info("add element: " + element + " to the end of the list");
        resizeIfNeeded();
        elements[size] = element;
        size++;
    }

    private void resizeIfNeeded() {
        if (elements.length == size) {
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    @Override
    public void add(int index, T element) {
        log.info("add element: " + element + " to the list by index: "+index);
        Objects.checkIndex(index, size+1);
        resizeIfNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;

    }

    @Override
    public void set(int index, T element) {
        log.info("set element: " + element + " to the list by index: "+index);
        Objects.checkIndex(index, size);
        elements[index] = element;
    }

    @Override
    public T get(int index) {
        log.info("get element by index: "+index);
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public T getFirst() {
        log.info("get first element from the list");
        return get(0);
    }

    @Override
    public T getLast() {
        log.info("get last element from the list");
        return get(size - 1);
    }

    @Override
    public T remove(int index) {
        log.info("remove element from the list by index: "+index);
        Objects.checkIndex(index, size);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                log.info("list contains " + element);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        log.info("clear the list");
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public static <T> List<T> of(T... elements) {
        log.info("create list of elements");
        List<T> list = new ArrayList<>();
        Stream.of(elements).forEach(list::add);
        return list;
    }
}

