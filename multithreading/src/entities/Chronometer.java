package entities;

// Хронометр. Можно Runnable, можно как-то иначе.
// Раз в секунду генерирует «тактирующий импульс» для планировщика отчетов. При этом ничего не знает о планировщиках и их количестве.
public class Chronometer implements Runnable {
  private boolean cancelled = false;

  @Override
  public void run() {
    while (!cancelled && !Thread.currentThread().isInterrupted()) {
      try {
        synchronized (this) {
          this.wait(1000);
          this.notifyAll();
        }
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
