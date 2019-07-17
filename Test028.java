class Apple {
    int data = 0;
    void print(){
        System.out.println(this.data);
    }
}
public class Test028 {
    public static void main(String[] args){
        Apple t = new Apple();
        t.data = 10;
        t.print();

        Apple l = new Apple();
        l.data = 20;
        l.print();
    }
}