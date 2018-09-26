package report;

import report.tasks.ReportTask;
import util.ResourceUtil;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class report
 * Stores a map of words <Word, Amount of occurences>
 */
public class Report {
  private static final Logger logger = LoggerFactory.getLogger(Report.class);
  private final ReportTask task;

  Report(ReportTask task) {
    this.task = task;
  }

  public void processResourceUtil(ResourceUtil util) throws Exception {
    logger.info("Started processing resource [{}]", util);
    try (ResourceUtil.Resource resource =  util.getResource()) {
      while (resource.hasContent()) {
        String scannedWord = resource.readWord();
        scannedWord = cleanPunctuations(scannedWord);
        for (String w: scannedWord.split(" "))
          task.processWord(w);
      }
    } catch (IOException e) {
      logger.error("Error during processing resource [{}]", util);
      throw new ReportException(e);
    }
    logger.info("Ended processing resource [{}]", util);
  }

  /**
   * Method receives whole file's content as a String and processes it
   * @param content
   */
  public void processFileAsString(String content) throws Exception {

    String[] words = content.split(" ");
    for (String w: words) {
      task.processWord(w);
    }
  }

  @Override
  public String toString() {
    return task.generateResult();
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
    content = content.replaceAll("»", "");//
    content = content.replaceAll("\r", "");
    content = content.replaceAll("\n", " ");
    content = content.replaceAll("\t", " ");
    return content;
  }
}
