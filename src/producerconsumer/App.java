package producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    private static BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(10);

    private static void producer() {
        Random random = new Random();

        try {
            bq.put(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void consumer() throws InterruptedException{
        Thread.sleep(100);
        Random random = new Random();

        while (true) {
            Thread.sleep(100);
            if(random.nextInt(10) == 0) {
                Integer value = bq.take();
                System.out.println("Taken value is: " + value + " and Queue size is: " + bq.size());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                producer();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
