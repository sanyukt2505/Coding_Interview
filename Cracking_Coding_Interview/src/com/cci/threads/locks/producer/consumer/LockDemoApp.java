package com.cci.threads.locks.producer.consumer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemoApp {
  private static int counter = 0;
  private static Lock lock = new ReentrantLock();

  public static void increment() {
    lock.lock();
    counter++;
    lock.unlock();
  }

  public static void first() {
    for (int i = 0; i < 1000; i++) {
      increment();
    }
  }

  public static void second() {
    for (int i = 0; i < 1000; i++) {
      increment();
    }
  }

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
        first();
    });

    Thread t2 = new Thread(() -> {
        second();
    });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Counter: " + counter);
  }
}
