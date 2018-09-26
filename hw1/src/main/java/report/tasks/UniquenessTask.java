package report.tasks;

import java.util.Set;
import java.util.TreeSet;

public class UniquenessTask implements ReportTask {
  private final Set<String> uniqueWords;

  public UniquenessTask() {
    uniqueWords = new TreeSet<>();
  }


  @Override
  public void processWord(String word) throws UniquenessException {
    if (uniqueWords.contains(word))
      throw new UniquenessException("Word already exists");
    uniqueWords.add(word);
  }

  @Override
  public String generateResult() {
    return null;
  }

  public class UniquenessException extends Exception {
    public UniquenessException(String reason) { super(reason); }
  }
}
