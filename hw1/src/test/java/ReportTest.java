import static org.junit.Assert.assertEquals;

import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.junit.Test;

public class ReportTest {

  @Test
  public void wordsCountTest() {
    String content = "Margaret prefers dogs dogs are hilarious";

    Report r = new Report();
    r.processFileAsString(content);
    Set<Entry<String, Integer>> entries = r.getResults();
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
  public void toStringTest() {
    final StringBuilder b = new StringBuilder();
    b.append("Johan Bruce Bruce ");
    b.append("C# C# C");
    final String content = b.toString();


    Report r = new Report();
    r.processFileAsString(content);

    StringBuilder expected = new StringBuilder();
    expected.append(String.format("Report [%d]\n", r.hashCode()));
    /* The order may vary depending on internal data structure, need to reimplement */
    expected.append("Bruce : 2\n");
    expected.append("C : 1\n");
    expected.append("C# : 2\n");
    expected.append("Johan : 1\n");

    assertEquals(expected.toString(), r.toString());
  }
}
