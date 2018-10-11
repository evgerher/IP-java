import entities.Chronometer;
import entities.Producer;
import entities.ReportPlanner;
import entities.Reporter;
import entities.consumers.Consumer;
import entities.consumers.EternalAnalyzer;
import entities.consumers.EternalCollector;
import entities.consumers.EternalConsumer;
import entities.consumers.FirstValueConsumer;
import java.util.Scanner;

public class MainExample {

  public static void main(String[] args) {
    EternalAnalyzer eAnalyzer = new EternalAnalyzer();
    EternalCollector eCollector = new EternalCollector();
    EternalConsumer eConsumer = new EternalConsumer();
    FirstValueConsumer fConsumer = new FirstValueConsumer();

    Chronometer chronometer = new Chronometer();
    Thread chrono = new Thread(chronometer); // Скормить два элемента выше

    Thread rp1 = new Thread(new ReportPlanner(new Reporter[]{eAnalyzer, eConsumer}, 1500, chronometer));
    Thread rp2 = new Thread(new ReportPlanner(new Reporter[]{eCollector, fConsumer}, 2500, chronometer));

    Thread p1 = new Thread(new Producer(new Consumer[]{eAnalyzer, eCollector, eConsumer, fConsumer}));
    Thread p2 = new Thread(new Producer(new Consumer[]{eAnalyzer, eCollector, eConsumer, fConsumer}));
    Thread p3 = new Thread(new Producer(new Consumer[]{eAnalyzer, eCollector, eConsumer, fConsumer}));

    chrono.start();
    rp1.start();
    rp2.start();
    p1.start();
    p2.start();
    p3.start();

    Scanner sc = new Scanner(System.in);
    if (sc.next().equals("\n")) {
      p1.interrupt();
      p2.interrupt();
      p3.interrupt();
      rp1.interrupt();
      rp2.interrupt();
      chrono.interrupt();
    }
  }
}
    /*2. Задание.
    Создаём по одному потребителю каждого вида.
    Создаём два планировщика отчётов. Половину потребителей отдаём одному планировщику, половину другому.
    Создаём хронометр и скармливаем ему оба планировщика.
    Создаём три поставщика, скормив каждому из них всех потребителей.
    Всё это запускаем.
    Ждём пользовательского ввода. Если пользователь нажал enter, корректно останавливаем работу.
    В каждом случае нужно использовать минимально возможную синхронизацию.
    */