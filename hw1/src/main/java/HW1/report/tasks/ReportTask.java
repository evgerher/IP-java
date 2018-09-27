package HW1.report.tasks;

/**
 * ReportTask interface
 * Any task is expected to operate with the resource word by word
 */
public interface ReportTask {
  void processWord(String word) throws RuntimeException;
  String generateResult();
}
