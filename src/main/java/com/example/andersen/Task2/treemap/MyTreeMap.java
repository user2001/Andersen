package com.example.andersen.Task2.treemap;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode
@ToString
public class MyTreeMap<K, V> {
    private int size = 0;
    private Node root = null;

    protected class Node {
        public K key;
        public V value;
        public Node left = null;
        public Node right = null;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V getValueByKey(K target) {
        return findNode(target) != null ? findNode(target).value : null;
    }

    private Node findNode(Object target) {
        if (target == null) {
            throw new NullPointerException();
        }
        Comparable<? super K> k = (Comparable<? super K>) target;
        // the actual search
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }

    public boolean containsValue(Object element) {
        Objects.requireNonNull(element);
        return containsValueHelper(root, element);
    }

    private boolean containsValueHelper(Node current, Object element) {
        Comparable<? super V> v = (Comparable<? super V>) element;

        if (current == null) {
            return false;
        } else if (v.compareTo(current.value) < 0) {//go left
            return containsValueHelper(current.left, element);
        } else if (v.compareTo(current.value) > 0) {//go right
            return containsValueHelper(current.right, element);
        } else return true;
    }

    public boolean insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = new Node(key, value);
            size++;
            return true;
        } else {
            return insertHelper(root, key, value);
        }
    }

    private boolean insertHelper(Node node, K key, V value) {
        Comparable<? super K> k = (Comparable<? super K>) key;
        int cmp = k.compareTo(node.key);

        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(key, value);
                size++;
                return true;
            } else {
                return insertHelper(node.left, key, value);
            }
        } else if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(key, value);
                size++;
                return true;
            } else {
                return insertHelper(node.right, key, value);
            }
        } else {
            return false;
        }
    }

    public Set<K> keySet() {
        Set<K> set = new LinkedHashSet<K>();
        addInOrder(root, set);
        return set;
    }

    private void addInOrder(Node node, Set<K> set) {
        if (node == null) return;
        addInOrder(node.left, set);
        set.add(node.key);
        addInOrder(node.right, set);
    }

    public K getFirstKey() {
        Node node = root;
        if (node != null)
            while (node.left != null)
                node = node.left;
        return node.key;
    }

    public K getLastKey() {
        Node node = root;
        if (node != null)
            while (node.right != null)
                node = node.right;
        return node.key;
    }
}

