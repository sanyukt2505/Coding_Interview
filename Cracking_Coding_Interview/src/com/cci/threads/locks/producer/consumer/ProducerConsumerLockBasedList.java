package com.cci.threads.locks.producer.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockBasedList {
    private List<Integer> numbersList = new ArrayList<>();
    // Obtain a reentrant numbersLock
    Lock numbersLock = new ReentrantLock();

    // Create two conditions using the numbersLock object we created above
    Condition numbersAvailable = numbersLock.newCondition();
    Condition listHasSpace = numbersLock.newCondition();

    public void addToList(Integer integer) {
        numbersLock.lock();
        while (numbersList.size() > 10) {
            // Await for listHasSpace condition to occur
            try {
                listHasSpace.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        numbersList.add(integer);
        numbersAvailable.signalAll();
        numbersLock.unlock();
    }

    public Integer processList() {
        numbersLock.lock();
        try {
            while (numbersList.size() == 0) {
                try {
                    numbersAvailable.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            listHasSpace.signalAll();
            return numbersList.remove(0);
        }
        finally {
            numbersLock.unlock();
        }

    }
}
