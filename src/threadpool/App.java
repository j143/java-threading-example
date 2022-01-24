package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("starting task id: "+id);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End task id: "+id);
    }
}

public class App {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);

        for (int i=0; i<100; i++)
            es.submit(new Processor(i));

        es.shutdown();

    }
}
