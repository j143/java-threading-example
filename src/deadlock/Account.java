package deadlock;

import java.util.stream.DoubleStream;

public class Account {
    private double balance = 1000.00;

    public static void transfer(Account acc1, Account acc2, double amount) {
        acc2.updateBalance(amount);
        acc1.updateBalance(-amount);
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double x) {
        this.balance += x;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
