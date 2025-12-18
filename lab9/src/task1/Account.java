package task1;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final long id;
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(long id, int initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("initialBalance < 0");
        this.id = id;
        this.balance = initialBalance;
    }

    public long getId() { return id; }
    public ReentrantLock getLock() { return lock; }
    public int getBalance() { return balance; }

    public void deposit(int amount) {
        if (amount < 0) throw new IllegalArgumentException("deposit amount < 0");
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount < 0) throw new IllegalArgumentException("withdraw amount < 0");
        if (balance < amount) throw new IllegalArgumentException("insufficient funds");
        balance -= amount;
    }
}
