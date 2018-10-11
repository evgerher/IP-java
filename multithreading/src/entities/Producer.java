package entities;
//     Поставщик (producer) «случайных» long-чисел. Runnable. На вход принимает массив потребителей (consumers).
// Постоянно генерирует новое случайное число и передаёт его всем потребителям;

import entities.consumers.Consumer;
import java.util.Random;

public class Producer implements Runnable {
  private final Consumer[] consumers;
  private final Random r;
  private boolean cancelled;

  public Producer(Consumer[] consumers) {
    this.consumers = consumers;
    synchronized (this) {
      try {
        this.wait(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      r = new Random(System.currentTimeMillis());
    }
    cancelled = false;
  }

  @Override
  public void run() {
    while (!cancelled) {
      long val = Math.abs(r.nextLong()) % 30;
//      System.out.println(val);
      for (Consumer c : consumers)
        c.consume(val);
    }
  }
}
