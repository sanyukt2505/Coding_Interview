package com.cci.threads.locks.diningphilosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private Lock lock;
    private int number;

    public Chopstick(int n) {
        lock = new ReentrantLock();
        this.number = n;
    }

    public void pickup() {
        lock.lock();
    }

    public void putDown() {
        lock.unlock();
    }

    public int getNumber() {
        return number;
    }
}
