class A {
    int apple = 10;
}
class B extends A{
    int add( int i, int j){ return 100;}
}
public class Test027 {
    public static void main(String[] args){
        B t = new B();
        System.out.println(t.apple);
    }
}