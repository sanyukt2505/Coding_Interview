package com.cci.threads.locks.diningphilosopher;

/**
 * Prioritized Chopsticks Solution
 */
public class Philosopher implements Runnable {
    private int bites = 10;
    private Chopstick lower, higher;
    private int index;

    public Philosopher(int i, Chopstick left, Chopstick right) {
        index = i;
        if (left.getNumber() < right.getNumber()) {
            this.lower = left;
            this.higher = right;
        } else {
            this.lower = right;
            this.higher = left;
        }
    }

    public void eat() {
        pickUp();
        chew();
        putDown();
    }

    public void pickUp() {
        lower.pickup();
        higher.pickup();
    }

    public void chew() {
        System.out.println("Chewing food");
    }

    public void putDown() {
        higher.putDown();
        lower.putDown();
    }

    @Override
    public void run() {
        for (int i = 0; i < bites; i++) {
            eat();
        }
    }
}
