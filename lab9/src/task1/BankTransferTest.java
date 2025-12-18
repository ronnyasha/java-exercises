package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.CountDownLatch;

public class BankTransferTest {
    public static void main(String[] args) throws InterruptedException {
        final int ACCOUNTS = 200;
        final int THREADS = 4000;
        final int MAX_INIT = 10_000;
        final int MAX_TRANSFER = 500;

        SplittableRandom rnd = new SplittableRandom(123);
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < ACCOUNTS; i++) {
            accounts.add(new Account(i, rnd.nextInt(0, MAX_INIT + 1)));
        }

        Bank bank = new Bank();
        long before = bank.totalBalance(accounts);
        System.out.println("TOTAL BEFORE = " + before);

        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(THREADS);

        for (int t = 0; t < THREADS; t++) {
            Thread worker = new Thread(() -> {
                try {
                    start.await();
                    int fromIdx = rnd.nextInt(ACCOUNTS);
                    int toIdx = rnd.nextInt(ACCOUNTS);
                    while (toIdx == fromIdx) toIdx = rnd.nextInt(ACCOUNTS);

                    int amount = rnd.nextInt(0, MAX_TRANSFER + 1);
                    bank.transfer(accounts.get(fromIdx), accounts.get(toIdx), amount);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
            worker.start();
        }

        start.countDown();
        done.await();

        long after = bank.totalBalance(accounts);
        System.out.println("TOTAL AFTER  = " + after);
        System.out.println("MATCH = " + (before == after));
    }
}
