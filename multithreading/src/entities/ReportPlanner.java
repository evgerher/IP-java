package entities;
//    Планировщик отчётов. Runnable. Принимает на вход период формирования отчета в секундах и массив отчётов.
// Использует «тактирующий импульс» хронометра. Генерирует отчеты не чаще заданного периода (но можно реже). Ничего не знает о хронометре.

public class ReportPlanner implements Runnable {
  private final Reporter[] reporters;
  private final int updateTime;
  private boolean cancelled;
  private final Chronometer chrono;

  public ReportPlanner(Reporter[] reporters, int period, Chronometer chrono) {
    this.reporters = reporters;
    this.updateTime = period;
    this.chrono = chrono;
  }

  @Override
  public void run() {
    while (!cancelled && !Thread.currentThread().isInterrupted()) {
      try {
        synchronized (chrono) {
          synchronized (this) {
            this.wait(updateTime);
          }
        }
        for (Reporter r : reporters)
          r.describe();

      } catch (InterruptedException e) {
        cancelled = true;
        Thread.currentThread().interrupt();
      }
    }
  }

  public void cancel() {
    cancelled = true;
  }
}
