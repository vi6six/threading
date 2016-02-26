package com.fibonacci;

import java.util.List;
import java.util.concurrent.*;

public class PerformanceTesterImpl implements PerformanceTester {
    private final List<Long> executionTime;
    private final CountDownLatch countDownLatch;

    public PerformanceTesterImpl(List<Long> executionTime, CountDownLatch countDownLatch) {
        this.executionTime = executionTime;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public PerformanceTestResult runPerformanceTest(Runnable task, int executionCount, int threadPoolSize)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        long start = System.currentTimeMillis();

        for (int i = 0; i < executionCount; i++) {
            executorService.execute(task);
        }

        // wait until all tasks are finished
        countDownLatch.await();

        long totalTime = System.currentTimeMillis() - start;
        executorService.shutdown();

        long minTime = executionTime.stream().mapToLong(Long::longValue).min().getAsLong();
        long maxTime = executionTime.stream().mapToLong(Long::longValue).max().getAsLong();

        return new PerformanceTestResult(totalTime, minTime, maxTime);
    }
}
