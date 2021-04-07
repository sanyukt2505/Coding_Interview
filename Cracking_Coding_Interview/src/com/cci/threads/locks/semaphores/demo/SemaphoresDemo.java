package com.cci.threads.locks.semaphores.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
  INSTANCE;

  // Allow "3" threads to download at the same time, "true" to make it fair
  private Semaphore semaphore = new Semaphore(3, true);

  public void downloadData() {
    try {
      semaphore.acquire();
      download();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }
  }

  private void download() {
    System.out.println(Thread.currentThread().getName() + " Downloading data...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class SemaphoresDemo {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

    // Create 12 downloads. Run 3 downloads at a time
    for (int i = 0; i < 12; i++) {
      executorService.execute(() -> {
        Downloader.INSTANCE.downloadData();
      });
    }

    executorService.shutdown();
  }
}
