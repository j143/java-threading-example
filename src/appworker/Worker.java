package appworker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();
    private List<Integer> list = new ArrayList<Integer>();

    public synchronized void stageOne() {
        // to simulate that it is getting some info from
        // somewhere
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.add(random.nextInt());
    }

    public synchronized void stageTwo() {
        // do nothing
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
//        process();
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
