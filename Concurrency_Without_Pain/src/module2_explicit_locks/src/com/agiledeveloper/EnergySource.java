package module2_explicit_locks.src.com.agiledeveloper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class EnergySource {
  private final long MAXLEVEL = 100;
  private long level = MAXLEVEL;
  private long usageCount;
  private Lock lock = new ReentrantLock();

  private static ScheduledExecutorService timer =
      Executors.newScheduledThreadPool(10);

  public EnergySource() {
  }

  private void init() {
    timer.scheduleAtFixedRate(this::replenish, 0, 1, TimeUnit.SECONDS);
  }

  public static void stopAllEnergySources() {
    timer.shutdown();
  }

  public long getUnitsAvailable() throws InterruptedException {
    return runMutuallyExclusive(() -> level);
  }

  public long getUsageCount() throws InterruptedException {
    return runMutuallyExclusive(() -> usageCount);
  }

  public boolean useEnergy(final long units) {
    return runMutuallyExclusive(
        () -> {
          if (units > 0 && level >= units) {
            level -= units;
            usageCount++;
            return true;
          }
          return false;
        });
  }

  private void replenish() {
    runMutuallyExclusive(
        () -> {
          if (level < MAXLEVEL) level++;
          return level;
        });
  }

  private <T> T runMutuallyExclusive(Supplier<T> code) {
    try {
      if (lock.tryLock(10, TimeUnit.SECONDS)) {
        try {
          return code.get();
        } finally {
          lock.unlock();
        }
      } else {
        throw new RuntimeException("Unable to get lock.... timed out");
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static EnergySource create() {
    EnergySource energySource = new EnergySource();
    energySource.init();
    return energySource;
  }
}
