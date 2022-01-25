package producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    private static BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(10);

    /**
     * producer random number queue
     *
     * @throws InterruptedException
     */
    private static void producer() throws InterruptedException {
        Random random = new Random();

        while(true) {
            bq.put(random.nextInt(100));
        }
    }

    /**
     * consumer - take a random number each time
     *
     * @throws InterruptedException
     */
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
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
