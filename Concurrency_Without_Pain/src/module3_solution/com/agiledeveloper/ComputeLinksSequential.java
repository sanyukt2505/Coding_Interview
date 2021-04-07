package module3_solution.com.agiledeveloper;
import java.util.*;
import java.util.concurrent.*;

public class ComputeLinksSequential implements ComputeLinks {
  private Set<String> visited = new HashSet<>();
  
  public void visitSubLinks(String url) {
    visited.add(url);
    
    LinksFinder.getLinks(url)
               .stream()
               .filter(link -> !visited.contains(link))
               .forEach(this::visitSubLinks);
  }
  
  public long countLinks(String url) {
    visitSubLinks(url);
    return visited.size();
  }
}
