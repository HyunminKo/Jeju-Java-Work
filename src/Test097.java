class Toilet {

    public void bigWork(String by) {
        synchronized (this) {
            System.out.println("SETP 1." + by);
            System.out.println("SETP 2." + by);
            System.out.println("SETP 3." + by);
            System.out.println("SETP 4." + by);
            System.out.println("SETP 5." + by);
        }
    }

    public synchronized void sleepWork(String by) {
        System.out.println("SLEEP SETP 1." + by);
        System.out.println("SLEEP SETP 2." + by);
        System.out.println("SLEEP SETP 3." + by);
    }
}

class A implements Runnable {

    private Toilet toilet = null;

    public A(Toilet t) {
        toilet = t;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            int time = (int) (Math.random() * 300);
            if (time % 2 == 0) {
                toilet.bigWork("Apple");
            } else {
                toilet.sleepWork("Apple");
            }

            try {
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
            int time = (int) (Math.random() * 300);
            if (time % 2 == 0) {
                toilet.bigWork("Banana");
            } else {
                toilet.sleepWork("Banana");
            }
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }
    }
}

public class Test097 {
    public static void main(String[] args) {
        Toilet t = new Toilet();
        new Thread(new A(t)).start();
        new Thread(new B(t)).start();
    }
}