package HW1;

import HW1.report.tasks.WordCounterTask;
import HW1.util.ResourceUtil;
import HW1.util.ResourceUtilFactory;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import HW1.report.*;

public class MainExample {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainExample.class);

  private static Path getResourcePath(String resource) throws URISyntaxException {
    final URL url = MainExample.class.getClassLoader().getResource(resource);
    return Paths.get(url.toURI()).toAbsolutePath();
  }

  public static void main(String[] args) throws URISyntaxException, MalformedURLException {
    example();
//    /* Expected to be files */
//    if (args.length < 2)
//      System.err.println("Incorrect amount of parameters");
//    List<File> files = new LinkedList<>();
  }

  /**
   * Code example for url
   */
  private static void example() throws MalformedURLException, URISyntaxException {
    List<ResourceUtil> resources;
    Report<WordCounterTask> r = new Report(new WordCounterTask());

    URL url = new URL("https://cfl.dropboxstatic.com/static/css/sprites/web_sprites-vflv2MHAO.css");
    File a = new File("C:/AiOLog.txt");
    File b = getResourcePath("poem.txt").toFile();

    try {
      resources = ResourceUtilFactory.createResourceUtils(a, b, url);
      for (ResourceUtil util: resources)
        r.processResourceUtil(util);
    } catch (ReportException | IOException e) {
      logger.error("God damn it, even in the example it fails");
    } catch (Exception f) { // TODO: UPDATE
      logger.error("THINK HOW TO AVOID THIS TOP TYPE EXCEPTION");
    }
  }
}