package com.example.andersen.treeset;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyTreeSet<T extends Comparable<T>> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;

        private Node(T element) {
            this.element = element;
        }

        public static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private Node<T> root;
    private int size = 0;

    public boolean insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = Node.valueOf(element);
            size++;
            return true;
        } else {
            return insertHelper(root, element);
        }
    }

    private boolean insertHelper(Node<T> node, T element) {

        if (element.compareTo(node.element) < 0) {
            if (node.left == null) {
                node.left = new Node(element);
                size++;
                return true;
            } else {
                return insertHelper(node.left, element);
            }
        } else if (element.compareTo(node.element) > 0) {
            if (node.right == null) {
                node.right = new Node(element);
                size++;
                return true;
            } else {
                return insertHelper(node.right, element);
            }
        } else {
            return false;
        }
    }

    public boolean containsValue(T element) {
        Objects.requireNonNull(element);
        return containsElementHelper(root, element);
    }

    private boolean containsElementHelper(Node<T> current, T element) {
        if (current == null) {
            return false;
        } else if (current.element.compareTo(element) < 0) {
            return containsElementHelper(current.right, element);
        } else if (current.element.compareTo(element) > 0) {
            return containsElementHelper(current.left, element);
        } else {
            return true;
        }
    }

    public T getFirst() {
        Node node = root;
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
            return (T) node.element;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T getLast() {
        Node node = root;
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
            return (T) node.element;
        } else {
            throw new NoSuchElementException();
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        root = null;
    }
}
