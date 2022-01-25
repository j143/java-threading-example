package reentrant;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    private Lock lock1 = new ReentrantLock();
    private Condition conditonLock1 = lock1.newCondition();

    public void firstThread() throws InterruptedException {
        lock1.lock();

        System.out.println("waiting...");

        conditonLock1.await();
        System.out.println("Woke up.");

        try {
            increment();
        }
        finally {
            lock1.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(100);
        lock1.lock();

        System.out.println("Press return key");
        new Scanner(System.in).nextLine();
        System.out.println("Response received.");

        conditonLock1.signal();

        try {
            increment();
        } finally {
            lock1.unlock();
        }
    }

    private void increment() {
        for(int i=0; i<1000; i++)
            count++;
    }

    public void finished() {
        System.out.println("Value of count is: " + count);
    }
}
