package appworker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();

    // use seperate locks for independent methods
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    public synchronized void stageOne() {
        // to simulate that it is getting some info from
        // somewhere
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list1.add(random.nextInt());
    }

    public synchronized void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list2.add(random.nextInt());
    }

    public void process() {
        for(int i=0; i<100000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting time: " + System.currentTimeMillis());
        long startTime = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t1.start();
        long endTime = System.currentTimeMillis();

        System.out.println("total time to run: " + (endTime - startTime));
    }
}
