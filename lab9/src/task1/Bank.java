package task1;

import java.util.List;

public class Bank {
    private static final Object tieLock = new Object();

    public void transfer(Account from, Account to, int amount) {
        if (from == null || to == null) throw new IllegalArgumentException("null account");
        if (amount < 0) throw new IllegalArgumentException("amount < 0");
        if (from == to || amount == 0) return;

        int fromHash = System.identityHashCode(from);
        int toHash   = System.identityHashCode(to);

        if (fromHash < toHash) {
            lockBothAndTransfer(from, to, amount);
        } else if (fromHash > toHash) {
            lockBothAndTransfer(to, from, amount);
        } else {
            synchronized (tieLock) {
                lockBothAndTransfer(from, to, amount);
            }
        }
    }

    private void lockBothAndTransfer(Account first, Account second, int amount) {
        first.getLock().lock();
        try {
            second.getLock().lock();
            try {
                if (first.getBalance() < amount) return;
                first.withdraw(amount);
                second.deposit(amount);
            } finally {
                second.getLock().unlock();
            }
        } finally {
            first.getLock().unlock();
        }
    }

    public long totalBalance(List<Account> accounts) {
        accounts.stream()
                .sorted((a, b) -> Integer.compare(System.identityHashCode(a), System.identityHashCode(b)))
                .forEach(a -> a.getLock().lock());
        try {
            long sum = 0;
            for (Account a : accounts) sum += a.getBalance();
            return sum;
        } finally {
            for (int i = accounts.size() - 1; i >= 0; i--) accounts.get(i).getLock().unlock();
        }
    }
}
