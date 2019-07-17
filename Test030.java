class A {
    A() {
        System.out.println("A constructor");
    }
    void print() {
        System.out.println("A print");
    }
}
class B extends A {
    B(){
        System.out.println("B constructor");
    }
    void print() {
        System.out.println("A print");
        super.print();
    }
}
public class Test030 {
    public static void main(String[] args){
        B t = new B();
        t.print();

        A t2 = new A();
        t2.print();

    }
}