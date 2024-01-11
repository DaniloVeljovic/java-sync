package threadsync;

public class VolatileKeywordExample {
    private static volatile boolean sayHello = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while(!sayHello) {

            }

            System.out.println("Hello world!");

            while(sayHello) {

            }

            System.out.println("Goodbye!");
        });

        thread.start();

        Thread.sleep(1000);
        System.out.println("Say hello...");
        sayHello = true;

        Thread.sleep(1000);
        System.out.println("Say goodbye...");
        sayHello = false;
    }
}
