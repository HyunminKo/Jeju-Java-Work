class Apple {
    int i;
    int add(int i , int j){
        return i + j;
    }
    Apple(int i){
        this.i = i;
    }
}

public class Test025 {
    public static void main(String[] args){
        Apple t = new Apple(6);
        System.out.println(t.i + t.add(10,20));
    }
}