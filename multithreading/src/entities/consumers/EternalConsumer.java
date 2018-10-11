package entities.consumers;
//     «Вечный потребитель». Реализация потребителя.
// Запоминает последнее потреблённое. Реализация отчета. Выводит своё имя и последнее потреблённое или 0.

import entities.Reporter;

public class EternalConsumer implements Consumer, Reporter {
  private long value;

  @Override
  public void describe() {
    String description = String.format("%s :: %d", toString(), value);
    System.out.println(description);
  }

  @Override
  public synchronized void consume(long value) {
    this.value = value;
  }
}
