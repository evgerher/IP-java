package HW1;

import HW1.report.tasks.ReportTask;
import HW1.report.tasks.UniquenessTask;
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
//    exampleCounter();
//    exampleUnique();
    if (args.length < 2) {
      logger.error("Incorrect amount of arguments");
    }
    String type = args[0];

    ReportTask task;
    if (type.equals("counter")) {
      task = new WordCounterTask();
    } else if (type.equals("unique")) {
      task = new UniquenessTask();
    } else {
      logger.error("User provided wrong op-type [{}]", type);
      throw new RuntimeException("Wrong operation type");
    }

    Report report = new Report(task);

  }

  /**
   * Code example counting values
   */
  private static void exampleCounter() throws MalformedURLException, URISyntaxException {
    List<ResourceUtil> resources;
    Report<WordCounterTask> r = new Report(new WordCounterTask());

    URL url = new URL("https://cfl.dropboxstatic.com/static/css/sprites/web_sprites-vflv2MHAO.css");
    File a = new File("C:/AiOLog.txt");
    File b = getResourcePath("poem.txt").toFile();

    try {
      resources = ResourceUtilFactory.createResourceUtils(a, b, url);
      for (ResourceUtil util: resources)
        r.processResourceUtil(util);
      System.out.println(r.toString());
    } catch (ReportException | IOException e) {
      logger.error("God damn it, even in the example it fails");
    }
  }

  private static void exampleUnique() throws URISyntaxException {
    File f = getResourcePath("unique.txt").toFile();

    try {
      ResourceUtil util = ResourceUtilFactory.createResourceUtil(f);
      Report<UniquenessTask> report = new Report<>(new UniquenessTask());
      report.processResourceUtil(util);
      System.out.println(report.toString());
    } catch (IOException | ReportException e) {
      logger.error("Report exception occured [{}]", e.getMessage());
    }
  }
}
