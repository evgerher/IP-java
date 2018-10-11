package entities.consumers;
//     «Вечный накопитель». Реализация потребителя и отчета. Накапливает сумму всех потреблённых значений.
// Выводит свое имя и сумму или 0.

import entities.Reporter;

public class EternalCollector implements Consumer, Reporter {
  private long value = 0;

  @Override
  public void describe() {
    String description = String.format("%s :: %d", toString(), value);
    System.out.println(description);
  }

  @Override
  public synchronized void consume(long value) {
    this.value += value;
  }
}
