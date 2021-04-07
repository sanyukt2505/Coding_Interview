package com.cci.threads.locks.producer.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
  // Obtain a Lock object of type ReentrantLock
  private Lock lock = new ReentrantLock();

  //  Use the above numbersLock object to obtain the Condition object
  private Condition condition = lock.newCondition();

  private List<Integer> list = new ArrayList<>();

  public void produce() throws InterruptedException {
	lock.lock();
	System.out.println("Producer method..");
	condition.await();
	System.out.println("Producer method again..");
  }

  public void consume() throws InterruptedException {
	lock.lock();
	Thread.sleep(2000);
	System.out.println("Consumer method..");
	Thread.sleep(3000);
	condition.signal();
	lock.unlock();
  }

}

public class LockAPIBasedSolution {
  public static void main(String[] args) {
	Worker worker = new Worker();
	Thread t1 = new Thread(() -> {
	  try {
		worker.produce();
	  } catch (InterruptedException e) {
		e.printStackTrace();
	  }
	});
	Thread t2 = new Thread(() -> {
	  try {
		worker.consume();
	  } catch (InterruptedException e) {
		e.printStackTrace();
	  }
	});

	t1.start();
	t2.start();
  }
}
