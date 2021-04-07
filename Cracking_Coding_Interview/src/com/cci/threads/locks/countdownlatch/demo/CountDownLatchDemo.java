package com.cci.threads.locks.countdownlatch.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {
  private int id;
  private CountDownLatch latch;
  private Random random;

  public Worker(int id, CountDownLatch latch) {
    this.id = id;
    this.latch = latch;
    this.random = new Random();
  }

  @Override
  public void run() {
    doWork(this.latch.getCount());
    this.latch.countDown();
  }

  private void doWork(long count) {
    System.out.println("Thread with ID " + this.id + " starts working.. Count: " + count);
    try {
      Thread.sleep(random.nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class CountDownLatchDemo {

  public static void main(String[] args) {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    CountDownLatch latch = new CountDownLatch(5);

    for (int i = 0; i < 5; i++) {
      executorService.execute(new Worker(i+1, latch));
    }

    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("All subroutines are finished... starting of main process");

    executorService.shutdown();
  }
}
