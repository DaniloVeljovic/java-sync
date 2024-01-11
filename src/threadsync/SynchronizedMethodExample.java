package threadsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedMethodExample {
    static class SyncCounter {
        int count = 0;

        public void incrementSyncBlock() {
            synchronized (this) {
                count += 1;
            }
        }

        public synchronized void increment() {
            count += 1;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        SyncCounter syncCounter = new SyncCounter();

        for(int i = 0; i < 1000; i++) {
            executorService.submit(syncCounter::increment);
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("The count is: " + syncCounter.getCount());
    }
}
