package HW1.report.tasks;

public interface ReportTask {
  void processWord(String word) throws Exception;
  String generateResult();
}
