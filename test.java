abstract class Bank {
    abstract public double calc(double money, int years, double rate);
    public void print() {
        double r = calc(100,100,0.066);
        System.out.println(r);
    }
}
class BokriBank extends Bank {
    @Override
    public double calc(double moeny, int years, double rate){
        return 59665.1234;
    }
}
class DankriBank extends Bank {
    @Override
    public double calc(double money, int years, double rate){
        return 760.0;
    }
}
public class test {
    public static void main(String[] args){
        Bank b = new DankriBank();
        b.print();
    }
}