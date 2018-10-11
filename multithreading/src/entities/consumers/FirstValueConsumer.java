package entities.consumers;
//     «Потребитель первого». Реализация потребителя, запоминающая первое полученное значение.
// Он же реализация отчёта, выводит свое название и запомненное значение или 0.

import entities.Reporter;

public class FirstValueConsumer implements Consumer, Reporter {
  private Long value;

  @Override
  public void describe() {
    String description;
    if (value == null)
      description = String.format("%s :: 0", this.toString());
    else
      description = String.format("%s :: %d", this.toString(), value);
    System.out.println(description);
  }


  @Override
  public synchronized void consume(long value) {
    if (this.value == null)
      this.value = value;
  }
}
