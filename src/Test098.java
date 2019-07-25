class A implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("A running");
        }
    }
}

class B implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("B running");
        }
    }
}

class C implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("C running");
        }
    }
}

public class Test098 {
    public static void main(String[] args) {
        Thread[] thread = new Thread[3];

        thread[0] = new Thread(new A());
        thread[1] = new Thread(new B());
        thread[2] = new Thread(new C());

        try {
            for (int i = 0; i < 3; i++) {
                thread[i].start();
            }
            for (int i = 0; i < 3; i++) {
                thread[i].join();
            }
        } catch (Exception e) {

        }
        System.out.println("최종 정리");
    }
}