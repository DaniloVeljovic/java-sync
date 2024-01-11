package threadsync;

public class MemoryConsistencyErrorExample {
    private static boolean sayHello = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while(!sayHello) {

            }

            System.out.println("Hello world!");

            while(sayHello) {

            }
            System.out.println("Good bye!");
        });

        thread.start();

        Thread.sleep(1000);
        System.out.println("Say hello...");

        sayHello = true;

        Thread.sleep(1000);
        System.out.println("Say bye...");
        sayHello = false;

    }
}
