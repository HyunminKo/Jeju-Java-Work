interface ICalc {
    public void print();
}
interface IUNKnown{}
class Apple {}
class Calc extends Apple implements ICalc, IUnknown{
    public void print() {System.out.println("A");}
}
public class Test038 {
    public static void main (String[] args){
        ICalc ic = new Calc();
    }
}