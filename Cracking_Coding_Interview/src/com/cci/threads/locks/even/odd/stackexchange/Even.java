package com.cci.threads.locks.even.odd.stackexchange;


public class Even implements Runnable {
  private final Monitor sharedObject;

  public Even(Monitor sharedObject) {
    this.sharedObject = sharedObject;
  }

  @Override
  public void run() {
    try {
      printEven();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void printEven() throws InterruptedException {
    synchronized (sharedObject) {
      for (int i = 1; i < 10; i++) {
        if (i % 2 == 0) {
          while (sharedObject.isOdd()) {
            sharedObject.wait();
          }
          System.out.println("Even: " + i);
          sharedObject.setOdd(true);
          sharedObject.notifyAll();
        }
      }
    }
  }

}
