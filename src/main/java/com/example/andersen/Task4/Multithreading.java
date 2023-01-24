package com.example.andersen.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Multithreading {
    public static final int FIXED_THREADS = 10;

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread, "ordinary_thread");
        Callable task = new MyThread();

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(FIXED_THREADS);


        List<Future> taskList = new ArrayList<>();
        for (int i = 0; i < FIXED_THREADS; i++) {
            Future future = fixedThreadPool.submit(task);
            taskList.add(future);
        }
        taskList.add(singleThreadExecutor.submit(task));


        thread1.start();
        thread1.join();

        fixedThreadPool.shutdown();
        singleThreadExecutor.shutdown();


        Map<String, Integer> resultMap = MyThread.threadMap;
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " ,value counter is: " + entry.getValue());
        }

    }

}
