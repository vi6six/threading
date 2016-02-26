package com.fibonacci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Example arguments:
 * 600 25 4
 */
public class Application {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Please use int args: 'NUMBER','TIMES','THREADS'");
            System.exit(1);
            return;
        }

        int num = Integer.parseInt(args[0]);
        int runTimes = Integer.parseInt(args[1]);
        int threads = Integer.parseInt(args[2]);
        if (num < 0) throw new IllegalArgumentException("Num should be not negative");
        if (runTimes < 0) throw new IllegalArgumentException("Num should be not negative");
        if (threads < 0) throw new IllegalArgumentException("Num should be not negative");

        // sync due to concurrent modifications by Tasks
        List<Long> executionsTime = Collections.synchronizedList(new ArrayList<>(runTimes));
        CountDownLatch countDownLatch = new CountDownLatch(runTimes);
        PerformanceTester performanceTester = new PerformanceTesterImpl(executionsTime, countDownLatch);
        Task task = new Task(num, executionsTime, countDownLatch);

        PerformanceTestResult result = null;

        try {
            result = performanceTester.runPerformanceTest(task, runTimes, threads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
