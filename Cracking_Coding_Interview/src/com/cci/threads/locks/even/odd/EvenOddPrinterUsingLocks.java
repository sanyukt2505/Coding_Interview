package com.cci.threads.locks.even.odd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddPrinterUsingLocks {

    static class NumberPrinter {
        Lock numberLock = new ReentrantLock();
        Condition evenPrinted = numberLock.newCondition();
        Condition oddPrinted = numberLock.newCondition();

        // Flag to check if the even number was printed
        private boolean isEvenPrinted = true;

        public void printOdd(int number) throws InterruptedException {
            try {
                if (numberLock.tryLock()) {

                    // Wait until even is not printed
                    while (!isEvenPrinted) {
                        evenPrinted.await();
                    }
                    // Print odd number
                    System.out.println(number);

                    // Set the share variable isEvenPrinted to false
                    isEvenPrinted = false;

                    // Notify the other waiting thread which is waiting on NumberPrinter
                    // This other thread will get out of waiting state
                    oddPrinted.signalAll();
                }
            } finally {
                numberLock.unlock();
            }

        }

        public void printEven(int number) throws InterruptedException {
            try {
                if (numberLock.tryLock()) {
                    while (isEvenPrinted) {
                        oddPrinted.await();
                    }

                    // print even number
                    System.out.println(number);

                    // Set the shared variable isEvenPrinted to true
                    isEvenPrinted = true;

                    evenPrinted.signalAll();
                }
            } finally {
                numberLock.unlock();
            }
        }
    }

    private static class EvenNumberGenerator implements Runnable {
        private NumberPrinter printer;
        private int max;

        public EvenNumberGenerator(NumberPrinter printer, int max) {
            this.printer = printer;
            this.max = max;
        }

        @Override
        public void run() {
            for (int i = 2; i <= max; i = i + 2) {
                try {
                    printer.printEven(i);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static class OddNumberGenerator implements Runnable {
        private NumberPrinter printer;
        private int max;

        public OddNumberGenerator(NumberPrinter printer, int max) {
            this.printer = printer;
            this.max = max;
        }

        @Override
        public void run() {
            for (int i = 1; i <= max; i = i + 2) {
                try {
                    printer.printOdd(i);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int maxNumber = 10;
        NumberPrinter printer = new NumberPrinter();
        new Thread(new EvenNumberGenerator(printer, maxNumber)).start();
        new Thread(new OddNumberGenerator(printer, maxNumber)).start();
    }
}
