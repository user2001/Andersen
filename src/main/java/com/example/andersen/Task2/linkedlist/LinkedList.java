package com.example.andersen.Task2.linkedlist;

import com.example.andersen.Task2.arraylist.List;
import lombok.extern.log4j.Log4j;

import java.util.NoSuchElementException;
import java.util.Objects;

@Log4j
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
        log.info("create list of elements");
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
            log.info("add element to the empty linkedList");
            first = last = newNode;
        } else {
            log.info("add element to the end of the linkedList");
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
            log.info("add element: " + element + " to the linkedList  when it`s empty");
            first = last = newNode;
        } else if (index == 0) {
            log.info("add element: " + element + " to the start of linkedList");
            first = newNode;
        } else if (index == size) {
            log.info("add element: " + element + " to the end of linkedList");
            last.next = newNode;
            last = newNode;
        } else {
            log.info("add element: " + element + " to the index: "+index+" of linkedList");
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
        log.info("set element " + element + " to the index");
        Objects.checkIndex(index, size);
        Node<T> node = getNodeByIndex(index);
        node.element = element;
    }

    @Override
    public T get(int index) {
        log.info("get element from list by index: " + index);
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            log.error("List is empty");
            throw new NoSuchElementException();
        }
        log.info("getting first element from list");
        return get(0);
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            log.error("List is empty");
            throw new NoSuchElementException();
        }
        log.info("getting last element from list");
        return get(size - 1);
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removed;
        if (index == 0) {
            log.info("removing first element from list");
            removed = first.element;
            first = first.next;
            if (first == null) {
                last = null;
            }

        } else {
            log.info("remove element from list by index: "+index);
            Node<T> prev = getNodeByIndex(index - 1);
            removed = prev.next.element;
            prev.next = prev.next.next;
            if (index == size - 1) {
                log.info("removing last element from list");
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
                log.info("list contains " + element);
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
        log.info("clear the list");
        first = last = null;
        size = 0;
    }


}
