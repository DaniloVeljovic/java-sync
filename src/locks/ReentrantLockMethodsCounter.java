package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMethodsCounter {
    private final ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    public int incrementAndGet() {
        System.out.println("IsLocked: " + lock.isLocked());

        System.out.println("IsHeldByCurrentThread: " + lock.isHeldByCurrentThread());

        boolean isAcquired = lock.tryLock();
        System.out.println("Lock Acquired: " + isAcquired);

        if(isAcquired) {
            try{
                Thread.sleep(2000);
                count += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lock.unlock();
            }
        }

        return count;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ReentrantLockMethodsCounter lockMethodsCounter = new ReentrantLockMethodsCounter();

        executorService.submit(() -> {
            System.out.println("IncrementCount (First Thread): " + lockMethodsCounter.incrementAndGet());
        });

        executorService.submit(() -> {
            System.out.println("IncrementCount (Second Thread): " + lockMethodsCounter.incrementAndGet());
        });

        executorService.shutdown();
    }
}
