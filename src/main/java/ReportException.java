/**
 * Report exceptions wrapper
 */
public class ReportException extends Exception {
  public ReportException(String desc) {
    super(desc);
  }

  public ReportException(String desc, Throwable err) {
    super(desc, err);
  }

  public ReportException(Throwable err) {
    super(err);
  }
}
