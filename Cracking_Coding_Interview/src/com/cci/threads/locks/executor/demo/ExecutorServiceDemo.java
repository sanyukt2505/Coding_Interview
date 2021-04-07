package com.cci.threads.locks.executor.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {
  @Override
  public void run() {
    for (int i =0; i < 5; i++) {
      try {
        Thread.sleep(300);
        System.out.println(i);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
}

public class ExecutorServiceDemo {
  public static void main(String[] args) {
    System.out.println("Demo: Fixed Thread Pool");
    ExecutorService fixedExecutorsService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 5; i++) {
      fixedExecutorsService.execute(new Worker());
    }
    fixedExecutorsService.shutdown();

    /*
    System.out.println("Demo: Single Thread Executor");
    ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 5; i++) {
      singleExecutorService.execute(new Worker());
    }
    singleExecutorService.shutdown();
    */

    /*
    System.out.println("Demo: Cached Thread Pool");
    ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 5; i++) {
      cachedExecutorService.execute(new Worker());
    }
    cachedExecutorService.shutdown();
    */
  }
}
