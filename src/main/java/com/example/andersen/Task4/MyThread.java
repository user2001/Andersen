package com.example.andersen.Task4;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Runnable, Callable {

    private static final AtomicInteger counter = new AtomicInteger();
    public static final Map<String, Integer> threadMap = new ConcurrentHashMap<>();

    private static int incrementCounter() {
        return counter.incrementAndGet();
    }

    private Object threadProcedure() {
        return threadMap.put(Thread.currentThread().getName(), incrementCounter());
    }

    public void run() {
        threadProcedure();
    }

    @Override
    public Object call() throws Exception {
        return threadProcedure();
    }
}
