package module3_solution.com.agiledeveloper;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class ComputeLinksConcurrent implements ComputeLinks {
  private ExecutorService executorService;
  private Set<String> visited = new HashSet<>();
  private AtomicReference<Exception> exception = new AtomicReference<>();
  private AtomicLong tasksCount = new AtomicLong(0);
  private CountDownLatch latch = new CountDownLatch(1);

  public long countLinks(String url) {
    try {
      executorService = Executors.newFixedThreadPool(
          Runtime.getRuntime().availableProcessors() * 10);

      if(shouldVisit(url))
        scheduleVisitToLink(url);

      if(!latch.await(15, TimeUnit.MINUTES))
        throw new RuntimeException("Timeout");

      if(exception.get() != null)
        throw new RuntimeException(exception.get());

      return visited.size();
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    } finally {
      executorService.shutdown();
    }
  }

  private void scheduleVisitToLink(String url) {
    tasksCount.incrementAndGet();
    executorService.submit(() -> visitLink(url));
  }

  public boolean shouldVisit(String link) {
    synchronized (visited) {
      return visited.add(link);
    }
  }

  private void visitLink(String url) {
    try {
      LinksFinder.getLinks(url)
                 .stream()
                 .filter(this::shouldVisit)
                 .forEach(this::scheduleVisitToLink);
      tasksCount.decrementAndGet();

      if(tasksCount.get() == 0)
        latch.countDown();
    } catch(Exception ex) {
      exception.set(ex);
      latch.countDown();
    }
  }
}
