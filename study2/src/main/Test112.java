package main;

public class Test112 {
    public static void test(String ... args){
        System.out.println(args);
    }
    public static void test2(Object... args){
        for (int i = 0; i < args.length; i++) {
            if(args[i] == null){
                System.out.println("null");
            }else if(args[i] instanceof Integer){
                System.out.println(((Integer) args[i]).intValue() + 1);
            }else if(args[i] instanceof Double){
                System.out.println(((Double)args[i]).doubleValue() + 0.1);
            }else if(args[i] instanceof String ){
                System.out.println((String)args[i]);
            }
        }
    }
    public static void main(String[] args) {
        test2(100,3.14,"hello");

        test();
        test("apple");
        test("apple","banana");
    }
}
