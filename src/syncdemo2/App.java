package syncdemo2;

public class App {

    private int count = 0;
    public static void main(String[] args) {
        App app = new App();
        app.doSomething();
    }

    public void doSomething() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10000; i++) {
                    count = count + 1;
                }
            }
        });

        t1.start();
        try {
            t1.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count value is: " + count);
    }
}
