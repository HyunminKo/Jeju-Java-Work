class A {

}
class B extends A {
    public void print() {System.out.println(100);}
}
public class Test061 {
    public static void main (String[] args){
        A t = new B();
        // t.print();

        B t2 = (B) t;
        t2.print();

        A t3 = new A();
        //error, class cast exeception
        

        if( t3 instanceof B){
            System.out.println("True");
            B t4 = (B) t3;
            t4.print();
        }else {
            System.out.println("False");
        }
    }
}