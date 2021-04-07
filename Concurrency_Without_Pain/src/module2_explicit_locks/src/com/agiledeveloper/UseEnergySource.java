package module2_explicit_locks.src.com.agiledeveloper;

import java.util.concurrent.*;

public class UseEnergySource {
  public static void main(String[] args) throws Exception {
    final EnergySource energySource = EnergySource.create();

    ExecutorService executorService = Executors.newFixedThreadPool(50);

    for(int i = 0; i < 50; i++) {
      executorService.execute(() -> energySource.useEnergy(1));
    }
    executorService.shutdown();

    Thread.sleep(1000);
    System.out.println("Available: " + energySource.getUnitsAvailable());
    EnergySource.stopAllEnergySources();

    System.out.println(energySource.getUsageCount());
  }
}
