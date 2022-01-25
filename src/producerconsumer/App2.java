package producerconsumer;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

class Processor {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock1 = new Object();

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock1) {
                if(list.size() == LIMIT) {
                    lock1.wait();
                }

                value++;
                list.add(value);
                lock1.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();

        while (true) {
            synchronized (lock1) {

                if(list.size() == 0) {
                    lock1.wait();
                }
                System.out.print("Length of list is: " + list.size());
                int value = list.removeFirst();
                System.out.print("; Value removed is: " + value);
                lock1.notify();
            }

            //sleep on average half a second
            Thread.sleep(random.nextInt(1000));

        }
    }

}

public class App2 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Processor process = new Processor();
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Processor process = new Processor();
                try {
                    process.consume();
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
