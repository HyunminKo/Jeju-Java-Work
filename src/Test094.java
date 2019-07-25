class A implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Apple");

            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

class B implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Banana");

            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

public class Test094 {
    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
    }
}