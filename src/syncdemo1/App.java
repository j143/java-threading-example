package syncdemo1;

import java.util.Scanner;

class processor extends Thread {

    // https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html
    // Atomic actions cannot be interleaved, so they can be used without fear of thread interference. However,
    // this does not eliminate all need to synchronize atomic actions, because memory consistency errors are
    // still possible. Using volatile variables reduces the risk of memory consistency errors, because any
    // write to a volatile variable establishes a happens-before relationship with subsequent reads of that
    // same variable.
    //
    // Reads and writes are atomic for all variables declared volatile (including long and double variables).
    private volatile boolean running = true;

    @Override
    public void run() {
//        super.run();
        while(running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean shutdown() {
        return running = false;
    }

}

public class App {
    public static void main(String[] args) {
        processor proc1 = new processor();
        proc1.start();

        System.out.println("press Enter to stop ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();
    }
}
