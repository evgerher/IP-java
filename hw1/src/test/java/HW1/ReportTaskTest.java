package HW1;

import static org.junit.Assert.assertEquals;

import HW1.report.Report;
import HW1.report.tasks.UniquenessTask;
import HW1.report.tasks.WordCounterTask;
import HW1.util.ResourceUtil;
import HW1.util.ResourceUtilFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.junit.Test;

public class ReportTaskTest {
  private Path getResourcePath(String resource) throws URISyntaxException {
    final URL url = ReportTaskTest.class.getClassLoader().getResource(resource);
    return Paths.get(url.toURI()).toAbsolutePath();
  }


  @Test
  public void wordsCountTest() throws Exception {
    String content = "Margaret prefers dogs dogs are hilarious";

    Report<WordCounterTask> r = new Report<>(new WordCounterTask());
    r.processFileAsString(content);
    WordCounterTask task = r.getTask();
    Set<Entry<String, Integer>> entries = task.getResults();
    TreeMap<String, Integer> expected = new TreeMap<String, Integer>() {{
      put("Margaret", 1);
      put("dogs", 2);
      put("are", 1);
      put("prefers", 1);
      put("hilarious", 1);
    }};

    assertEquals(entries.size(), expected.size());

    for (Entry<String, Integer> entry: entries) {
      String key = entry.getKey();
      Integer val = entry.getValue();
      Integer expectedVal = expected.get(key);
      assertEquals(expectedVal, val);
    }
  }

  @Test
  public void toStringWordCountTest() throws Exception {
    final StringBuilder b = new StringBuilder();
    b.append("Johan Bruce Bruce ");
    b.append("C# C# C");
    final String content = b.toString();


    Report<WordCounterTask> r = new Report(new WordCounterTask());
    r.processFileAsString(content);

    StringBuilder expected = new StringBuilder();
    expected.append("WordCount task\n");
    /* The order may vary depending on internal data structure, need to reimplement */
    expected.append("Bruce : 2\n");
    expected.append("C : 1\n");
    expected.append("C# : 2\n");
    expected.append("Johan : 1\n");

    assertEquals(expected.toString(), r.toString());
  }

  @Test
  public void uniqueTaskTest() throws Exception {
    final File f = getResourcePath("unique-2.txt").toFile();
    ResourceUtil util = ResourceUtilFactory.createResourceUtil(f);

    Report<UniquenessTask> r = new Report(new UniquenessTask());
    r.processResourceUtil(util);

    r.getTask().getResults();
  }

  @Test(expected = UniquenessTask.UniquenessException.class)
  public void uniqueExceptionTest() throws URISyntaxException, IOException {
    final File f = getResourcePath("exception-case.txt").toFile();
    final ResourceUtil util = ResourceUtilFactory.createResourceUtil(f);

    Report<UniquenessTask> r = new Report<>(new UniquenessTask());
    r.processResourceUtil(util);
  }
}
