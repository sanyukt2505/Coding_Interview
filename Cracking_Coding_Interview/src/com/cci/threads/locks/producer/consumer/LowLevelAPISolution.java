package com.cci.threads.locks.producer.consumer;

import java.util.ArrayList;
import java.util.List;

class Processor {
  private List<Integer> list = new ArrayList<>();
  private final int LIMIT = 10;
  private Object lock = new Object();

  public void produce() throws InterruptedException {
    int value = 0;

    while (true) {
      synchronized (lock) {
        while (this.list.size() == LIMIT) {
          lock.wait();
        }
        // Insert new value into list
        this.list.add(value);
        System.out.println("Producer method added " + value);
        value++;
        lock.notify();

      }
    }
  }

  public void consume() throws InterruptedException {

    while(true) {
      synchronized (lock) {
        while (this.list.size() == 0) {
          lock.wait();
        }
        // Consume value from the list
        int value = this.list.remove(0);
        System.out.println("Consumer method removed " + value);
        lock.notify();
      }

      Thread.sleep(1000);
    }

  }

}

public class LowLevelAPISolution {
  static Processor processor = new Processor();

  public static void main(String[] args) {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          processor.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          processor.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();
  }
}
