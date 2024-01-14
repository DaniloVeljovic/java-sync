package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicValuesExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger atomicInteger  = new AtomicInteger(0);

        for(int i = 0; i < 100; i++) {
            executorService.submit(atomicInteger::incrementAndGet);
        }

        System.out.println("The final value is: " + atomicInteger);
    }
}
