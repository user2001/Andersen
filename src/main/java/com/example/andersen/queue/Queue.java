package com.example.andersen.queue;

public class Queue {
    int SIZE = 5;
    int items[] = new int[SIZE];
    int front, rear, count;
    public final static int INIT_NUM = -1;

    public Queue() {
        front = INIT_NUM;
        rear = INIT_NUM;
    }

    public boolean isFull() {
        if (front == 0 && rear == SIZE - 1) {
            return true;
        }
        return false;
    }


    public boolean isEmpty() {
       return front==INIT_NUM;
    }

    public void add(int element) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (front == -1)
                front = 0;
            rear++;
            items[rear] = element;
            System.out.println("Inserted " + element);
            count++;
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Program Terminated");
            System.exit(-1);
        }
        return items[front];
    }

    public int remove() {
        int element;
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return (INIT_NUM);
        } else {
            element = items[front];
            if (front >= rear) {
                front = INIT_NUM;
                rear = INIT_NUM;
            } else {
                front++;
                count--;
            }
            System.out.println("Deleted -> " + element);
            return (element);
        }
    }

    public int size() {
        return count;
    }

}
