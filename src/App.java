
class Runner extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i=0; i<10; i++) {
            System.out.println("Hello" + i);
        }
    }
}

public class App {

    public void main(String[] args) {
        //
    }
}
