import java.util.*;

public class Test070 {
    public static void main(String[] args){
        Map<String,String> map = new Hashtable<>();
        map.put("apple","사과");
        map.put("banana","바나나");
        map.put("orange","오렌지");
        map.put("kiwi","키위");
        System.out.println(map.toString());

        String value = map.get("apple");
        System.out.println(value);

        Set<String> keys = map.keySet();
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String k = it.next();
            String v = map.get(k);
            System.out.println(k + ": " + v);
        }
    }
}