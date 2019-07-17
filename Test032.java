class A {
    int i = 100;
    void print() {System.out.println("A print");}
}
class B extends A {
    int i = 200;
    void print() {System.out.println("B print");}
    void print2() {System.out.println("B print2");}
}
public class Test032 {
    public static void main(String[] args){
        A t = new B();
        t.print();
        System.out.println(t.i);
    }
}