import java.util.*;
static class HW {
    /*
        0 < randomValue < limit
    */
    static int[] randomArray(int nums, int limit){
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < nums; i++){
            double r = (int)(Math.random());
            
        }
    }
    /* 
        "[]"
        "['apple']"
        "['apple','banana']"
        "['apple','banana','orange']"
    */
    static String collectionString(List<String> list){

    }
}
public class Test072_HW {
    public static void main(String[] args){
        int[] r1 = HW.randomArray(10);
        for(int i = 0 ; i < r1.length; i++){
            System.out.println(r1[i]);
        }

        List<String> ls = new ArrayList<>();
        ls.add("apple");
        ls.add("banana");
        ls.add("orange");
        ls.add("kiwi");

        String r2 = HW.collectionString(ls);
        System.out.println(r2);
    }
}