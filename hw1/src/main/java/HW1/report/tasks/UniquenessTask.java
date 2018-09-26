package HW1.report.tasks;

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
    StringBuilder b = new StringBuilder();
    for (String w: uniqueWords)
      b.append(String.format("Word [%s]\n", w));
    b.append(String.format("Total amount: %d\n", uniqueWords.size()));
    return  b.toString();
  }

  public class UniquenessException extends Exception {
    public UniquenessException(String reason) { super(reason); }
  }
}
