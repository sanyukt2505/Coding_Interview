package com.cci.threads.locks.even.odd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vijay on 3/23/16.
 */
public class PrintNumbers {
  private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  private Lock numbersLock = new ReentrantLock();

  private Condition numberEven = numbersLock.newCondition();
  private Condition numberOdd = numbersLock.newCondition();

  private void printOdd() {
    numbersLock.lock();
    try {
      for (int i = 1; i < numbers.length; i++) {
        while (numbers[i] % 2 == 0) {
          try {
            numberOdd.await();
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
        }
        System.out.println("Next Odd: " + numbers[i]);
        numberEven.signalAll();
      }
    } finally {
      numbersLock.unlock();
    }
  }

  private void printEven() {
    numbersLock.lock();
    try {
      for (int i = 1; i < numbers.length; i++) {
        while (numbers[i] % 2 != 0) {
          try {
            numberEven.await();
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
        }
        System.out.println("Next Even: " + numbers[i]);
        numberOdd.signalAll();
      }
    } finally {
      numbersLock.unlock();
    }
  }

  public static void main(String[] args) {
    PrintNumbers printNumbers = new PrintNumbers();
    Thread t1 = new Thread(() -> {
      printNumbers.printEven();
    });
    Thread t2 = new Thread(() -> {
      printNumbers.printOdd();
    });
  }
}
