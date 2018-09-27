package HW1.report.tasks;

public interface ReportTask {
  void processWord(String word) throws RuntimeException;
  String generateResult();
}
