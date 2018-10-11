package entities.consumers;
//     Потребитель (consumer). Интерфейс. Декларирует метод принимающий число для «потребления».

public interface Consumer {
  void consume(long value);
}
