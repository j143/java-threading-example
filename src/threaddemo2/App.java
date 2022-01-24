package threaddemo2;

class Runner extends Thread {

    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println("Hello" + i);
        }
    }
}

public class App {

    public static void main(String[] args) {
    }
}
