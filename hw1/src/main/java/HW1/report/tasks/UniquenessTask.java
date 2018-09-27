package HW1.report.tasks;

import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniquenessTask implements ReportTask {
  private static final Logger logger = LoggerFactory.getLogger(UniquenessTask.class);

  private final Set<String> uniqueWords;

  public UniquenessTask() {
    uniqueWords = new TreeSet<>();
  }


  @Override
  public void processWord(String word) throws UniquenessRuntimeException {
    if (uniqueWords.contains(word)) {
      logger.error("Uniqueness of elements violation");
      throw new UniquenessRuntimeException("Word already exists");
    }
    uniqueWords.add(word);
  }

  @Override
  public String generateResult() {
    StringBuilder b = new StringBuilder();
    for (String w: uniqueWords)
      b.append(String.format("Word [%s]\n", w));
    b.append(String.format("Total amount: %d\n", uniqueWords.size()));
    return  b.toString();
  }

  public Set<String> getResults() {
    return uniqueWords;
  }

  public class UniquenessRuntimeException extends RuntimeException {
    public UniquenessRuntimeException(String reason) { super(reason); }
  }
}
