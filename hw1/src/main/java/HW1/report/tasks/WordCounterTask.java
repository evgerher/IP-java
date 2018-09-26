package HW1.report.tasks;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounterTask implements ReportTask {
  private final TreeMap<String, Integer> wordsCount;
  private final static Pattern numberPattern = Pattern.compile("[0-9]"); // Numbers detector

  public WordCounterTask() {
    wordsCount = new TreeMap<>();
  }

  /**
   * Method process one word
   * Checks is it empty string or contains number
   * @param word
   */
  @Override
  public void processWord(String word) {
    if (word.equals(""))
      return;
    if (containsDigit(word))
      return;
    word = word.trim();

    // Update table
    if (wordsCount.containsKey(word)) {
      int amount = wordsCount.get(word);
      wordsCount.put(word, amount + 1);
    } else
      wordsCount.put(word, 1);
  }

  @Override
  public String generateResult() {
    StringBuilder b = new StringBuilder();
    b.append(String.format("WordCount task [%s]\n", toString()));
    for (Map.Entry<String, Integer> entry: getResults()) {
      b.append(String.format("%s : %d\n", entry.getKey(), entry.getValue()));
    }

    return b.toString();
  }

  /**
   * Method checks does word contan a number
   * @param word
   * @return is number in a word or not
   */
  private boolean containsDigit(String word) {
    Matcher matcher = numberPattern.matcher(word);
    return matcher.find();
  }

  /**
   * Method returns table as set of entries
   * @return
   */
  public Set<Entry<String, Integer>> getResults() {
    return wordsCount.entrySet();
  }
}
