package com.example.andersen.Task4;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Runnable, Callable<Boolean> {

    private static final AtomicInteger counter = new AtomicInteger();
    public static final Map<String, Integer> threadMap = new ConcurrentHashMap<>();

    private static int incrementCounter() {
        return counter.incrementAndGet();
    }

    private Boolean threadProcedure() {
        String threadName=Thread.currentThread().getName();
        System.out.println("Thread: "+threadName+ " starts at: "+ LocalDateTime.now());
        threadMap.put(threadName, incrementCounter());
        System.out.print("Thread: "+threadName+" finish at: "+LocalDateTime.now()+"\n");
        return true;
    }

    public void run() {
        threadProcedure();
    }

    @Override
    public Boolean call() throws Exception {
        return threadProcedure();
    }
}
