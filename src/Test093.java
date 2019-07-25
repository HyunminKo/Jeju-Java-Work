class CustomThread implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("HelloWorld " + i);
        }
    }
}

public class Test093 {
    public static void main(String[] args) {
        Runnable rb = new CustomThread();
        new Thread(rb).start();
    }
}