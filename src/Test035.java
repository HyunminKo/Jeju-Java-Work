abstract class A {
    abstract public void print();
}
class B extends A {
    public void print(){
        System.out.println("test");
    }
}
public class Test035 {
    public static void main(String[] args){
        A t = new B();
        t.print();
    }
}