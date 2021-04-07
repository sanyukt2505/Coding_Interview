package module2_analyze.com.agiledeveloper;

import java.util.concurrent.*;

public class UseEnergySource {
  public static void main(String[] args) throws Exception {
    final EnergySource energySource = new EnergySource();

    ExecutorService executorService = Executors.newFixedThreadPool(50);

    for(int i = 0; i < 50; i++) {
      executorService.execute(() -> energySource.useEnergy(1));
    }
    executorService.shutdown();

    Thread.sleep(1000);
    System.out.println("Available: " + energySource.getUnitsAvailable());
  }
}
