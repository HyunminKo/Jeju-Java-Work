package main;
interface ITemp {
    public void print();
}
public class Test114 {
    public static void main(String[] args) {
        final int i = 100;
        int j = 100;
        ITemp t = new ITemp() {
            @Override
           public void print() {
                System.out.println("Overriding"+i+j);
           }
        };
        t.print();
    }
}
