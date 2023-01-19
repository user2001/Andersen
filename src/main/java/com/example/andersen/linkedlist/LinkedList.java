package com.example.andersen.linkedlist;

import com.example.andersen.arraylist.List;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> last;
    private Node<T> first;
    private int size;

    public static <T> List<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        for (T e : elements) {
            linkedList.add(e);
        }
        return linkedList;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;
        } else if (index == 0) {
            first = newNode;
        } else if (index == size) {
            last.next = newNode;
            last = newNode;
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        Node<T> node = getNodeByIndex(index);
        node.element = element;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removed;
        if (index == 0) {
            removed = first.element;
            first = first.next;
            if (first == null) {
                last = null;
            }

        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            removed = prev.next.element;
            prev.next = prev.next.next;
            if (index == size - 1) {
                last = prev;
            }
            size--;
        }
        return removed;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }


}
