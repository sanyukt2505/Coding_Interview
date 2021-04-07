package com.cci.threads.locks.even.odd;

import java.util.concurrent.Semaphore;

/**
 * Created by Vijay on 1/11/16.
 */
public class ThreadEvenOddSemaphore implements Runnable {
    private boolean isEven;
    private int count;

    static Semaphore s = new Semaphore(1);
    static Semaphore t = new Semaphore(0);

    ThreadEvenOddSemaphore(boolean flag, int c) {
        isEven = flag;
        count = c;
    }

    private void printOdd(int count) throws InterruptedException {
        int o = 1;

        for (int i = 0; i < count; i++) {
            s.acquire(1);
            System.out.println("Thread: "+Thread.currentThread().getName()+" prints " + o);
            o = o + 2;
            t.release(1);
        }
    }

    private void printEven(int count) throws InterruptedException {
        int e = 2;

        for (int j = 0; j < count; j++) {
            t.acquire(1);
            System.out.println("Thread: "+Thread.currentThread().getName()+" prints " + e);
            e = e + 2;
            s.release(1);
        }
    }

    @Override
    public void run() {
        if (isEven) {
            try {
                printEven(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                printOdd(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread a = new Thread(new ThreadEvenOddSemaphore(true, 20));
        Thread b = new Thread(new ThreadEvenOddSemaphore(false, 20));
        a.start();
        b.start();
    }
}
