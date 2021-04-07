package com.cci.threads.locks.even.odd;

public class EvenOddNumberPrinter {

    private static class NumberPrinter {
        // Flag to check if the even number was printed
        private boolean isEvenPrinted = true;

        public void printOdd(int number) throws InterruptedException {
            // Get a lock on NumberPrinter
            synchronized (this) {
                // Wait until even is not printed
                if (!isEvenPrinted) {
                    wait();
                }
                // Print odd number
                System.out.println(number);

                // Set the share variable isEvenPrinted to false
                isEvenPrinted = false;

                // Notify the other waiting thread which is waiting on NumberPrinter
                // This other thread will get out of waiting state
                notify();
            }
        }

        public void printEven(int number) throws InterruptedException {
            synchronized (this) {
                if (isEvenPrinted) {
                    wait();
                }

                System.out.println(number);
                isEvenPrinted = true;
                notify();
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
        private  int max;

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
