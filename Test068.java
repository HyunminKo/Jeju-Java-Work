import java.util.*;

public class Test068 {
    public static void main(String[]args) throws Exception {
        List<String> l = new ArrayList<>();
        l.add("apple");
        l.add("banana");
        l.add("orange");
        l.add("kiwi");

        System.out.println(l);

        List<String> m = new LinkedList<>();
        m.add("apple");
        m.add("banana");
        m.add("orange");
        m.add("kiwi");

        System.out.println(m);

        Set<String> set = new TreeSet<>();

        set.add("apple");
        set.add("banana");
        set.add("orange");
        set.add("kiwi");

        System.out.println(set);

        Set<String> set2 = new TreeSet<>();

        set2.add("apple");
        set2.add("banana");
        set2.add("orange");
        set2.add("kiwi");

        System.out.println(set2);

        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String str = it.next();
            if(str.indexOf("an") != -1){
                it.remove();
            }
        }
        System.out.println(set);
    }
}