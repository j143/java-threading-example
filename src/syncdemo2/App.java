package syncdemo2;

public class App {

    private int count = 0;

    // https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
    // private Object lock1 = new Object();

    // Second thread have to wait until the first thread completes the
    // execution due to instrinsic lock
    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {
        App app = new App();
        app.doSomething();
    }

    public void doSomething() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10000; i++) {
                    // read `count`, increment by 1 and store in `count`
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count value is: " + count);
    }
}
