package countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor implements Runnable {
    private CountDownLatch latch;

    Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Process started, with active Thread count: " + Thread.activeCount());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public static void main(String[] args) {
        CountDownLatch latch1 = new CountDownLatch(3);

        ExecutorService es = Executors.newFixedThreadPool(3);

        for(int i=0; i<100; i++) {
            es.submit(new Processor(latch1));
            System.out.println("---- " + i + " ----");
        }

        try {
            latch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
