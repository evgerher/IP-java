package entities.consumers;
//     «Великий уравнитель». Потребитель и отчёт.
// Подсчитывает сколько раз, какой остаток от деления на 10 встречался у потреблённых чисел.
// Выводит своё имя и список встреченных остатков и сколько раз они встретились.

import entities.Reporter;
import java.util.HashMap;
import java.util.Map;

public class EternalAnalyzer implements Consumer, Reporter {
  private HashMap<Integer, Integer> map = new HashMap<>();

  @Override
  public void describe() {
    StringBuilder b = new StringBuilder(toString());
    for(Map.Entry<Integer, Integer> item: map.entrySet()) {
      b.append(String.format(" [%d - %d]", item.getKey(), item.getValue()));
    }

    System.out.println(b.toString());
  }

  @Override
  public synchronized void consume(long value) {
    int reminder = (int)(value % 10);
    int amount = map.getOrDefault(reminder, -1);
    if (amount < 0) {
      map.put(reminder, 1);
    } else {
      map.put(reminder, amount + 1);
    }
  }
}
