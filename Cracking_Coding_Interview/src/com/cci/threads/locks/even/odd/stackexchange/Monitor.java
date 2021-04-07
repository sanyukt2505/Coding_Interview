package com.cci.threads.locks.even.odd.stackexchange;

public class Monitor {
  private volatile boolean isOdd;

  public boolean isOdd() {
    return isOdd;
  }

  public void setOdd(boolean isOdd) {
    this.isOdd = isOdd;
  }
}
