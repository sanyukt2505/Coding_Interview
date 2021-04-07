package module3_solution.com.agiledeveloper;
import java.util.*;
import java.util.function.*;

public class Timeit {
  public static Map<String, Object> measure(Supplier<Long> code) {
    long start = System.nanoTime();
    long count = code.get();
    long end = System.nanoTime();
    
    Map<String, Object> response = new HashMap<>();
    response.put("Time", (end - start)/1.0e9);
    response.put("result", count);
    return response;
  }
}