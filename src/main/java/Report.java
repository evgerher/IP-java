import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class report
 * Stores a map of words <Word, Amount of occurences>
 */
public class Report {
  private static final Logger logger = LoggerFactory.getLogger(Report.class);
  private final static Pattern numberPattern = Pattern.compile("[0-9]"); // Numbers detector

  private final TreeMap<String, Integer> wordsCount;

  Report() {
    wordsCount = new TreeMap<>();
  }

  /**
   * Method for processing list of files
   * @param files
   * @throws ReportException if any
   */
  public void processFiles(List<File> files) throws ReportException {
    for (File f: files)
      processFile(f);
  }

  /**
   * Method reads content of the file word by word and updates `wordsCount` object
   * @param file
   * @throws ReportException
   */
  public void processFile(File file) throws ReportException {
    logger.info("Started processing file [{}]", file.getName());
    try (ResourceUtil util = ResourceUtilFactory.createResourceUtil(file)) {
      processResource(util);
    } catch (IOException e) {
      logger.error("Error during processing file [{}]", file.getName());
      throw new ReportException(e);
    }
    logger.info("Ended processing file [{}]", file.getName());
  }

  public void processURL(URL url) throws ReportException {
    logger.info("Started processing url [{}]", url.toString());
    try (ResourceUtil util = ResourceUtilFactory.createResourceUtil(url)) {
      processResource(util);
    } catch (IOException e) {
      logger.error("Error during processing url [{}]", url.toString());
      throw new ReportException(e);
    }
    logger.info("Ended processing url [{}]", url.toString());
  }

  /**
   * Common method for both ResourceUtil types
   * @param util - ResourceUtil object
   * @throws IOException
   */
  private void processResource(ResourceUtil util) throws IOException {
    while (util.hasContent()) {
      String scannedWord = util.readWord();
      scannedWord = cleanPunctuations(scannedWord);
      for (String word: scannedWord.split(" "))
        processWord(word);
    }
  }

  /**
   * Method receives whole file's content as a String and processes it
   * @param content
   */
  public void processFileAsString(String content) {
    content = cleanPunctuations(content);

    String[] words = content.split(" ");
    for (String w: words) {
      if (w.equals(""))
        return;
      if (containsDigit(w))
        return;
      w = w.trim();
      if (wordsCount.containsKey(w)) {
        int amount = wordsCount.get(w);
        wordsCount.put(w, amount + 1);
      } else
        wordsCount.put(w, 1);
    }
  }

  /**
   * Method process one word
   * Checks is it empty string or contains number
   * @param word
   */
  private void processWord(String word) {
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

  /**
   * Method gets rid of punctuation marks
   * @param content
   * @return
   */
  private String cleanPunctuations(String content) {
    content = content.replaceAll("\\.", "");
    content = content.replaceAll("\\?", "");
    content = content.replaceAll("\\(", "");
    content = content.replaceAll("\\)", "");
    content = content.replaceAll("[ ]+-[ ]+", "");
    content = content.replaceAll(":", "");
    content = content.replaceAll(",", "");
    content = content.replaceAll("!", "");
    content = content.replaceAll("—", "");
    content = content.replaceAll(";", "");
    content = content.replaceAll("«", "");
    content = content.replaceAll("»", "");//-???
    content = content.replaceAll("\r", "");
    content = content.replaceAll("\n", " ");
    content = content.replaceAll("\t", " ");
    return content;
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
  //TODO: what is better here? Entry or own implementation of Pair?
  public Set<Entry<String, Integer>> getResults() {
    return wordsCount.entrySet();
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    b.append(String.format("Report [%d]\n", this.hashCode()));
    for (Entry<String, Integer> pair: wordsCount.entrySet()) {
      String row = String.format("%s : %d\n", pair.getKey(), pair.getValue());
      b.append(row);
    }

    return b.toString();
  }
}
