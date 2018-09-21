import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class MainExample {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainExample.class);

  private static Path getResourcePath(String resource) throws URISyntaxException {
    final URL url = MainExample.class.getClassLoader().getResource(resource);
    return Paths.get(url.toURI()).toAbsolutePath();
  }

  public static void main(String[] args) throws URISyntaxException {
//    fileExample();
//    urlExample();

    /* Expected to be files */
    if (args.length < 2)
      System.err.println("Incorrect amount of parameters");
    List<File> files = new LinkedList<>();
  }

  /**
   * Code example for files
   * @throws URISyntaxException
   */
  private static void fileExample() throws URISyntaxException {
    List<File> files = new LinkedList<>();
    files.add(new File("C:/AiOLog.txt"));
    files.add(getResourcePath("poem.txt").toFile());

    Report report = new Report();
    try {
      report.processFiles(files);
    } catch (ReportException e) {
      logger.error("Error during creation of report [%d]", report.hashCode());
    }
    System.out.println(report.toString());
  }

  /**
   * Code example for url
   */
  private static void urlExample() {
    Report r2 = new Report();
    try {
      URL url = new URL("https://cfl.dropboxstatic.com/static/css/sprites/web_sprites-vflv2MHAO.css");
      r2.processURL(url);
      System.out.println(r2);
    } catch (MalformedURLException e) {
      logger.error("Incorrect url passed");
    } catch (ReportException f) {
      logger.error("Error during creation of report [%d]", r2.hashCode());
    }
  }
}
