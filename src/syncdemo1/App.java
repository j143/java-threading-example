package syncdemo1;

import java.util.Scanner;

class processor extends Thread {

    private boolean running = true;

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
