package com.cci.threads.locks.even.odd.stackexchange;

public class Odd implements Runnable {
  private final Monitor sharedObject;

  public Odd(Monitor monitor) {
    this.sharedObject = monitor;
  }

  @Override
  public void run() {
    try {
      printOdd();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void printOdd() throws InterruptedException {
    synchronized (sharedObject) {
      for (int i = 1; i < 10; i++) {
        if (i % 2 != 0) {
          while(!sharedObject.isOdd()) {
            sharedObject.wait();
          }
          System.out.println("Odd: " + i);
          sharedObject.setOdd(false);
          sharedObject.notifyAll();
        }
      }
    }
  }
}
