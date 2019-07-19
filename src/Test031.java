class A {
    int i = 100;
    void print() System.out.println("A print");
}
class B extends A {
    void print() System.out.println("B print2");
}
public class Test031 {
    public static void main(String[] args){
        A t = new B();
        
    }
}