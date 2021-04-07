package module3_solution.com.agiledeveloper;
import java.net.URL;
import java.util.Set;
import java.util.Scanner;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toSet;

public class LinksFinder {
  public static Set<String> getLinks(String url) {
    try {
      String content = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
      return  Stream.of(content.split("<A href='"))
                    .skip(1)
                    .map(line -> line.split("'")[0])
                    .map(link -> toAbsoluteURL(url, link))
                    .collect(toSet());
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }
  
  public static String toAbsoluteURL(String baseURL, String link) {
    try {
      return link.startsWith("http") ? link : new URL(new URL(baseURL), link).toString();      
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}