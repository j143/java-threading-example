package deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{

    @Override
    public void run() {
        Runner runner = new Runner();
        runner.firstThread();
    }
}

public class App {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        es.submit(new Processor());
        es.shutdown();
    }
}
