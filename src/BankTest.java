import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankTest {
    public static final int ACCOUNTS = 100;  // Первоначальный баланс счета
    public static final double INIT_BALANCE = 1000; //Первоначальный баланс банка

    public static void main(String[] args) {
        Bank b = new Bank(ACCOUNTS, INIT_BALANCE);
        int i;
        for (i = 0; i < ACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INIT_BALANCE);
            Thread t = new Thread(r);    // Создаем новый поток
            t.start();
        }
    }
}

class Bank {
    private final double[] accounts;   // Массив счетов
    private Lock myLock;      // Объект блокировки
    private Condition cond1;  // Объект условия

    /**
     * @param n              – номер счета
     * @param initialBalance – первоначальный баланс для каждого счета
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;

        myLock = new ReentrantLock();  // - для блокировки
        cond1 = myLock.newCondition();
    }

    /**
     * Передача денег с одного счета на другой
     *
     * @param from   – со счета
     * @param to     – на счет
     * @param amount – количество передаваемых средств
     */
    public void remittance(int from, int to, double amount)
            throws InterruptedException {
        myLock.lock();
        try {
            // -для блокировки метода
            if (accounts[from] < amount) //return;// проверяем в цикле while
// Для применения объекта условия вместо return:
            cond1.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Общий баланс: %10.2f%n", getTotalBalance());
// для объекта условия -
            cond1.signalAll();
        } finally {
            myLock.unlock();
        } // -для освобождения метода
    }

    public double getTotalBalance() // Получение общей суммы
    {
        double sum = 0;
        for (double a : accounts) sum += a;
        return sum;
    }

    public int size()  // Запрос количества счетов
    {
        return accounts.length;
    }
}

class TransferRunnable implements Runnable  // Передача от одного другому
{
    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY = 10;

    /**
     * @param b    - банк
     * @param from – с какого счета
     * @param max  – макс. количество денег для каждой проводки
     */
    public TransferRunnable(Bank b, int from, double max) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.remittance(fromAccount, toAccount, amount);
                Thread.sleep((int) (DELAY * Math.random()));
            }
        } catch (InterruptedException e) {
        }
    }
}