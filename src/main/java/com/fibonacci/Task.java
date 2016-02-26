package com.fibonacci;

import java.util.List;
import java.util.concurrent.CountDownLatch;


public class Task implements Runnable {
    private final List<Long> executionTimeList;
    private final int n;
    private final CountDownLatch latch;

    public Task(int n, List<Long> executionTimeList, CountDownLatch latch) {
        this.n = n;
        this.executionTimeList = executionTimeList;
        this.latch = latch;
    }

    @Override
    public void run() {
        FibCalc fibCalc = new RecursiveFibCalcImpl();
        long start = System.currentTimeMillis();
        fibCalc.fib(n);
        long executionTime = System.currentTimeMillis() - start;
        executionTimeList.add(executionTime);

        // task is finished, reduce pending tasks counter
        latch.countDown();
    }
}
