package report.tasks;

import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import report.tasks.ReportTask;

/**
 * UniquenessTask checks that all the words in a resource(s) are unique
 */
public class UniquenessTask implements ReportTask {
  private static final Logger logger = LoggerFactory.getLogger(UniquenessTask.class);

  private final Set<String> uniqueWords;

  public UniquenessTask() {
    uniqueWords = new TreeSet<>();
  }


  /**
   * Add word to the set
   * @param word
   * @throws UniquenessRuntimeException if the word already exists
   */
  @Override
  public void processWord(String word) throws UniquenessRuntimeException {
    if (uniqueWords.contains(word)) {
      logger.error("Uniqueness of elements violation");
      throw new UniquenessRuntimeException("Word already exists");
    }
    uniqueWords.add(word);
  }

  /**
   *
   * @return string representation of the results
   */
  @Override
  public String generateResult() {
    StringBuilder b = new StringBuilder();
    for (String w: uniqueWords)
      b.append(String.format("Word [%s]\n", w));
    b.append(String.format("Total amount: %d\n", uniqueWords.size()));
    return  b.toString();
  }

  /**
   *
   * @return set representation of results
   */
  public Set<String> getResults() {
    return uniqueWords;
  }

  public class UniquenessRuntimeException extends RuntimeException {
    public UniquenessRuntimeException(String reason) { super(reason); }
  }
}
