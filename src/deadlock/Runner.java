package deadlock;

import java.util.Random;

public class Runner {
    private Account acc1 = new Account();
    private Account acc2 = new Account();

    public void firstThread() {
        Random random = new Random();

        Account.transfer(acc1,acc2,random.nextInt(100));
        System.out.println("Acc 1 balance: " + acc1.getBalance());
        System.out.println("Acc 2 balance: " + acc2.getBalance());
    }

}
