class Toilet {
    public void bigWork(String by) {
        System.out.println("SETP 1." + by);
        System.out.println("SETP 2." + by);
        System.out.println("SETP 3." + by);
        System.out.println("SETP 4." + by);
        System.out.println("SETP 5." + by);
    }
}

class A implements Runnable {

    private Toilet toilet = null;

    public A(Toilet t) {
        toilet = t;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            toilet.bigWork("Apple");
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

class B implements Runnable {

    private Toilet toilet = null;

    public B(Toilet t) {
        toilet = t;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            toilet.bigWork("Banana");
            try {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

public class Test095 {
    public static void main(String[] args) {
        Toilet t = new Toilet();
        new Thread(new A(t)).start();
        new Thread(new B(t)).start();
    }
}