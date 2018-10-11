import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import report.Report;
import report.ReportException;
import report.tasks.ReportTask;
import report.tasks.UniquenessTask;
import report.tasks.WordCounterTask;
import util.ResourceUtil;
import util.ResourceUtilFactory;

public class MainExample {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainExample.class);

  private static Path getResourcePath(String resource) throws URISyntaxException {
    final URL url = MainExample.class.getClassLoader().getResource(resource);
    return Paths.get(url.toURI()).toAbsolutePath();
  }

  /**
   * Type `unique` - checks all the words are unique in resource(s)
   * Type `counter` - counts words occurrences in resource(s)
   * @param args - first parameter is TYPE of operation, others - URIs to resources to process
   * @throws URISyntaxException if any
   * @throws MalformedURLException if any
   */
  public static void main(String[] args) throws URISyntaxException, MalformedURLException {
//    exampleCounter();
//    exampleUnique();
    if (args.length < 2) {
      logger.error("Incorrect amount of arguments");
    }
    String type = args[0];
    List<URI> uris = new LinkedList<>();
    for (int i = 1; i < args.length; i++)
      uris.add(new URI(args[i]));

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
    try {
      for (URI uri: uris) {
        ResourceUtil util = ResourceUtilFactory.createResourceUtil(uri);
        report.processResourceUtil(util);
      }
    } catch (IOException e) {
      logger.error("Error during processing ResourceUtil [{}]", e.getMessage());
    } catch (ReportException f) {
      logger.error("Report exception occured [{}]", f.getMessage());
    }
  }

  /**
   * Code example WORD COUNTER task execution
   */
  private static void exampleCounter() throws MalformedURLException, URISyntaxException {
    List<ResourceUtil> resources;
    Report<WordCounterTask> r = new Report(new WordCounterTask());

    URL url = new URL("https://cfl.dropboxstatic.com/static/css/sprites/web_sprites-vflv2MHAO.css");
    File a = new File("C:/AiOLog.txt");
    File b = getResourcePath("resources/poem.txt").toFile();

    try {
      resources = ResourceUtilFactory.createResourceUtils(a.toURI(), b.toURI(), url.toURI());
      for (ResourceUtil util: resources)
        r.processResourceUtil(util);
      System.out.println(r.toString());
    } catch (ReportException | IOException e) {
      logger.error("God damn it, even in the example it fails");
    }
  }

  /**
   * Example of UNIQUE task execution
   * @throws URISyntaxException
   */
  private static void exampleUnique() throws URISyntaxException {
    File f = getResourcePath("resources/unique.txt").toFile();

    try {
      ResourceUtil util = ResourceUtilFactory.createResourceUtil(f.toURI());
      Report<UniquenessTask> report = new Report<>(new UniquenessTask());
      report.processResourceUtil(util);
      System.out.println(report.toString());
    } catch (IOException | ReportException e) {
      logger.error("Report exception occured [{}]", e.getMessage());
    }
  }
}
